/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.alarm;

import model.observable.*;

public interface AlarmListener extends ObservableModelListener {

    void alarmStart();

    void alarmStop();

    void alarmIntervalChange();

    void alarmTimerEvent();
}
