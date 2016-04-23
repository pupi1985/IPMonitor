/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.notification;

import java.util.*;
import model.notification.performers.*;

public class MailNotification extends AbstractNotification {

    private static MailNotification instance;

    private MailNotification() {
    }

    public static MailNotification getInstance() {
        if (instance == null) {
            instance = new MailNotification();
        }
        return instance;
    }

    public void ipMonitorIPChangeFiltered(String fromIP, String toIP, Date lastChecked, boolean firstTime) {
        try {
            MailPerformer.getInstance().sendMail(fromIP, toIP);
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
