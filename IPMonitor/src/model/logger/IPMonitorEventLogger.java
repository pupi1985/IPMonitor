/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.logger;

import java.util.*;
import model.ipmonitor.*;

public class IPMonitorEventLogger implements IPMonitorListener, IPMonitorExceptionListener {

    public void ipMonitorIPChange(String fromIP, String toIP, Date lastChecked, boolean firstTime) {
        if (firstTime) {
            MainLogger.getInstance().write("Fetched initial IP " + toIP);
        } else {
            MainLogger.getInstance().write("IP changed from " + fromIP + " to " + toIP);
        }
    }

    public void ipMonitorStart() {
        MainLogger.getInstance().write("Started monitoring");
    }

    public void ipMonitorStop() {
        MainLogger.getInstance().write("Stopped monitoring");
    }

    public void ipMonitorIntervalChange() {
    }

    public void ipMonitorIPCheckStart() {
    }

    public void ipMonitorIPCheckEnd() {
    }

    public void ipMonitorIPNotFound() {
        MainLogger.getInstance().write("IP not found in WEB page");
    }

    public void ipMonitorIO() {
        MainLogger.getInstance().write("Could not find WEB page");
    }
}
