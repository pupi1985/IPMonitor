/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.notification;

import java.util.*;
import model.notification.performers.*;

public class VisualNotification extends AbstractNotification {

    private static VisualNotification instance;

    private VisualNotification() {
    }

    public static VisualNotification getInstance() {
        if (instance == null) {
            instance = new VisualNotification();
        }
        return instance;
    }

    public void ipMonitorIPChangeFiltered(String fromIP, String toIP, Date lastChecked, boolean firstTime) {
        VisualPerformer.getInstance().displayMessage(fromIP, toIP);
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
        return false;
    }
}
