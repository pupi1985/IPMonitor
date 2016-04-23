/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package controller.extras;

import java.util.Formatter;

public class TimeUnitConverter {

    public static final int MINUTES = 60;
    public static final int HOURS = 3600;
    private int hours;
    private int minutes;
    private int seconds;

    public TimeUnitConverter(int interval) {
        if (interval >= HOURS) {
            hours = interval / HOURS;
            interval = interval % HOURS;
        }
        if (interval >= MINUTES) {
            minutes = interval / MINUTES;
            interval = interval % MINUTES;
        }
        seconds = interval;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public String toString() {
        return new Formatter().format("%1$02d:%2$02d:%3$02d", hours, minutes,
                seconds).toString();
    }
}
