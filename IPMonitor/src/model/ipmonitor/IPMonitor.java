/*
 * IP Monitor is a simple application which monitors your public IP
 * address for changes and lets you set different kinds of notification
 * such as email, audio, pop up or executing a command. It can also run
 * in background as a Windows service or Linux/Mac daemon.
 *
 * Copyright (C) 2007 - 2016  Gabriel Zanetti http://github.com/pupi1985
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package model.ipmonitor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.LinkedList;

import model.alarm.Alarm;
import model.alarm.AlarmListener;
import model.configuration.ConfigurationManager;
import model.extras.CommonFunctions;
import model.ipmonitor.exceptions.InvalidIPAddressException;
import model.ipmonitor.exceptions.InvalidIntervalException;
import model.ipreader.IPReader;
import model.ipreader.exceptions.IPNotFoundException;
import model.notification.AbstractNotification;
import model.observable.ObservableModelUnique;

public class IPMonitor extends ObservableModelUnique<IPMonitorListener> {

    private Alarm alarm;
    private IPReader ipReader;
    private String lastIP;
    private LinkedList<IPMonitorExceptionListener> exceptionListeners;
    private boolean checking = false;
    private boolean lastCheckFailed = false;
    private Date lastChecked;
    private Date lastChange;

    public IPMonitor() {
        exceptionListeners = new LinkedList<>();
        lastIP = "";
        ipReader = new IPReader();
        alarm = new Alarm(5);
        alarm.addAlarmListener(new AlarmListenerImpl());
    }

    public synchronized void start() {
        if (!alarm.isOn()) {
            alarm.start();
        }
        if (ConfigurationManager.getInstance().isService()) {
            while (alarm.isOn()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public synchronized void stop() {
        alarm.stop();
        notifyAll();
    }

    public boolean isChecking() {
        return alarm.isOn();
    }

    public void beginCheckingIP() {
        if (this.checking) {
            return;
        }
        this.lastChecked = new Date();
        notifyIPCheckStart();
        new CheckingIPThread().start();
    }

    public void setInterval(int interval) throws InvalidIntervalException {
        if (interval < ConfigurationManager.getInstance().getMinCheckingInterval()) {
            throw new InvalidIntervalException();
        }
        alarm.setInterval(interval);
    }

    public int getInterval() {
        return alarm.getInterval();
    }

    public String getUrl() {
        return ipReader.getUrl();
    }

    public void setUrl(String url) throws MalformedURLException {
        ipReader.setUrl(url);
    }

    public String getLastIP() {
        return lastIP;
    }

    public void setLastIP(String lastIP) throws Exception {
        if (CommonFunctions.getInstance().isValidIP(lastIP)) {
            this.lastIP = lastIP;
        } else {
            throw new InvalidIPAddressException();
        }
    }

    public Date getLastChecked() {
        return lastChecked;
    }

    public void setLastChecked(Date lastChecked) {
        this.lastChecked = lastChecked;
    }

    public Date getLastChange() {
        return lastChange;
    }

    public void setLastChange(Date lastChange) {
        this.lastChange = lastChange;
    }

    public boolean hasNotification(AbstractNotification notification) {
        return listeners.contains(notification);
    }

    public void addIPMonitorListener(IPMonitorListener listener) {
        addModelListener(listener);
    }

    public void removeIPMonitorListener(IPMonitorListener listener) {
        removeModelListener(listener);
    }

    public void addIPMonitorExceptionListener(
            IPMonitorExceptionListener listener) {
        exceptionListeners.add(listener);
    }

    public void removeIPMonitorExceptionListener(
            IPMonitorExceptionListener listener) {
        exceptionListeners.remove(listener);
    }

    /* Listeners IPMonitor */
    private void notifyStart() {
        for (IPMonitorListener listener : listeners) {
            listener.ipMonitorStart();
        }
    }

    private void notifyStop() {
        for (IPMonitorListener listener : listeners) {
            listener.ipMonitorStop();
        }
    }

    private void notifyIntervalChange() {
        for (IPMonitorListener listener : listeners) {
            listener.ipMonitorIntervalChange();
        }
    }

    private void notifyIPCheckStart() {
        for (IPMonitorListener listener : listeners) {
            listener.ipMonitorIPCheckStart();
        }
    }

    private void notifyIPCheckEnd() {
        for (IPMonitorListener listener : listeners) {
            listener.ipMonitorIPCheckEnd();
        }
    }

    private void notifyIPChange(String fromIP, String toIP, Date lastChecked, boolean firstTime) {
        for (IPMonitorListener listener : listeners) {
            listener.ipMonitorIPChange(fromIP, toIP, lastChecked, firstTime);
        }
    }

    /* Listeners IPMonitorException */
    private void notifyIPNotFoundException() {
        for (IPMonitorExceptionListener listener : exceptionListeners) {
            listener.ipMonitorIPNotFound();
        }
    }

    private void notifySocketTimeoutException() {
        for (IPMonitorExceptionListener listener : exceptionListeners) {
            listener.ipMonitorTimeout();
        }
    }

    private void notifyIOException() {
        for (IPMonitorExceptionListener listener : exceptionListeners) {
            listener.ipMonitorIO();
        }
    }

    private class AlarmListenerImpl implements AlarmListener {

        public void alarmStart() {
            notifyStart();
        }

        public void alarmStop() {
            notifyStop();
        }

        public void alarmIntervalChange() {
            notifyIntervalChange();
        }

        public void alarmTimerEvent() {
            try {
                beginCheckingIP();
            } catch (Exception e) {
            }
        }
    }

    private class CheckingIPThread extends Thread {

        public void run() {
            try {
                String newIP = ipReader.getIP();
                if (!lastIP.equals(newIP)) {
                    String oldIP = lastIP;
                    lastIP = newIP;
                    boolean firstTime = oldIP.trim().isEmpty();
                    if (!firstTime) {
                        lastChange = lastChecked;
                    }
                    notifyIPChange(oldIP, newIP, lastChange, firstTime);
                } else {
                    if (lastCheckFailed) {
                        notifyIPChange(lastIP, newIP, lastChange, true);
                    }
                }
                lastCheckFailed = false;
            } catch (IPNotFoundException e) {
                notifyIPNotFoundException();
                lastCheckFailed = true;
            } catch (SocketTimeoutException e) {
                notifySocketTimeoutException();
                lastCheckFailed = true;
            } catch (IOException e) {
                notifyIOException();
                lastCheckFailed = true;
            } finally {
                notifyIPCheckEnd();
            }
            checking = false;
        }
    }
}
