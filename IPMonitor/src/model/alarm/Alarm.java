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

package model.alarm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.observable.ObservableModel;
import model.observable.ObservableModelListener;

public class Alarm extends ObservableModel {

    private static final int THOUSAND = 1000;
    private Timer timer;

    public Alarm(int interval) {
        timer = new Timer(interval, null);
        setInterval(interval);
        timer.addActionListener(new TimerListener());
    }

    public void stop() {
        timer.stop();
        notifyAlarmStop();
    }

    public void start() {
        timer.start();
        notifyAlarmStart();
    }

    public boolean isOn() {
        return timer.isRunning();
    }

    /* Listeners */
    public void addAlarmListener(AlarmListener listener) {
        addModelListener(listener);
    }

    public void removeAldarmListener(AlarmListener listener) {
        removeModelListener(listener);
    }

    private void notifyAlarmStart() {
        for (ObservableModelListener listener : listeners) {
            ((AlarmListener) listener).alarmStart();
        }
    }

    private void notifyAlarmStop() {
        for (ObservableModelListener listener : listeners) {
            ((AlarmListener) listener).alarmStop();
        }
    }

    private void notifyIntervalChange() {
        for (ObservableModelListener listener : listeners) {
            ((AlarmListener) listener).alarmIntervalChange();
        }
    }

    private void notifyTimerEvent() {
        for (ObservableModelListener listener : listeners) {
            ((AlarmListener) listener).alarmTimerEvent();
        }
    }

    /* Getters & Setters */
    public int getInterval() {
        return timer.getDelay() / THOUSAND;
    }

    public void setInterval(int interval) {
        timer.setDelay(interval * THOUSAND);
        notifyIntervalChange();
    }

    private class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            notifyTimerEvent();
        }
    }
}
