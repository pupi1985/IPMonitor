/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.notification;

import java.util.*;
import model.notification.performers.*;

public class CommandNotification extends AbstractNotification {

    private static CommandNotification instance;

    private CommandNotification() {
    }

    public static CommandNotification getInstance() {
        if (instance == null) {
            instance = new CommandNotification();
        }
        return instance;
    }

    public void ipMonitorIPChangeFiltered(String fromIP, String toIP, Date lastChecked, boolean firstTime) {
        try {
            CommandPerformer.getInstance().executeCommand(fromIP, toIP);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ipMonitorIPCheckStart() {
    }

    public void ipMonitorIPCheckEnd() {
    }

    public void ipMonitorIntervalChange() {
    }

    public void ipMonitorStart() {
    }

    public void ipMonitorStop() {
    }

    public boolean canBeNotifiedWhenRunningAsService() {
        return true;
    }
}
