/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.data;

import java.util.*;
import model.ipmonitor.*;

public class LastCheckServiceListener implements IPMonitorListener {

    private IPMonitor ipMonitor;

    public LastCheckServiceListener(IPMonitor ipMonitor) {
        this.ipMonitor = ipMonitor;
    }

    public void saveToFile() {
        new LastCheckPropertiesManager(ipMonitor).saveToFile();
    }

    public void ipMonitorIPCheckStart() {
        this.saveToFile();
    }

    public void ipMonitorIPCheckEnd() {
    }

    public void ipMonitorIntervalChange() {
    }

    public void ipMonitorStart() {
    }

    public void ipMonitorStop() {
    }

    public void ipMonitorIPChange(String fromIP, String toIP, Date lastChecked, boolean firstTime) {
        this.saveToFile();
    }
}