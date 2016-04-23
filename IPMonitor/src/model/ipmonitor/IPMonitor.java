/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.ipmonitor;

import java.io.IOException;
import java.net.MalformedURLException;
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
import model.observable.ObservableModelListener;
import model.observable.ObservableModelUnique;

public class IPMonitor extends ObservableModelUnique {

    private Alarm alarm;
    private IPReader ipReader;
    private String lastIP;
    private LinkedList<IPMonitorExceptionListener> exceptionListeners;
    private boolean checking = false;
    private boolean lastCheckFailed = false;
    private Date lastChecked;
    private Date lastChange;

    public IPMonitor() {
        exceptionListeners = new LinkedList<IPMonitorExceptionListener>();
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
                    e.printStackTrace();
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
        for (ObservableModelListener listener : listeners) {
            ((IPMonitorListener) listener).ipMonitorStart();
        }
    }

    private void notifyStop() {
        for (ObservableModelListener listener : listeners) {
            ((IPMonitorListener) listener).ipMonitorStop();
        }
    }

    private void notifyIntervalChange() {
        for (ObservableModelListener listener : listeners) {
            ((IPMonitorListener) listener).ipMonitorIntervalChange();
        }
    }

    private void notifyIPCheckStart() {
        for (ObservableModelListener listener : listeners) {
            ((IPMonitorListener) listener).ipMonitorIPCheckStart();
        }
    }

    private void notifyIPCheckEnd() {
        for (ObservableModelListener listener : listeners) {
            ((IPMonitorListener) listener).ipMonitorIPCheckEnd();
        }
    }

    private void notifyIPChange(String fromIP, String toIP, Date lastChecked, boolean firstTime) {
        for (ObservableModelListener listener : listeners) {
            ((IPMonitorListener) listener).ipMonitorIPChange(fromIP, toIP, lastChecked, firstTime);
        }
    }

    /* Listeners IPMonitorException */
    private void notifyIPNotFoundException() {
        for (IPMonitorExceptionListener listener : exceptionListeners) {
            listener.ipMonitorIPNotFound();
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
                e.printStackTrace();
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
                        //lastChange = lastChecked;
                        notifyIPChange(lastIP, newIP, lastChange, true);
                    }
                }
                lastCheckFailed = false;
            } catch (IPNotFoundException e1) {
                notifyIPNotFoundException();
                lastCheckFailed = true;
            } catch (IOException e2) {
                notifyIOException();
                lastCheckFailed = true;
            } finally {
                notifyIPCheckEnd();
            }
            checking = false;
        }
    }
}
