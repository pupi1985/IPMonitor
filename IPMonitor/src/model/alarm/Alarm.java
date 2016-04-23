/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.alarm;

import model.observable.ObservableModelListener;
import model.observable.ObservableModel;
import java.awt.event.*;
import javax.swing.*;
import model.*;

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
