/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.notification;

import java.util.Date;
import model.notification.performers.*;

public class AudioNotification extends AbstractNotification {

    private static AudioNotification instance;

    private AudioNotification() {
    }

    public static AudioNotification getInstance() {
        if (instance == null) {
            instance = new AudioNotification();
        }
        return instance;
    }

    public void ipMonitorIPChangeFiltered(String fromIP, String toIP, Date lastChecked, boolean firstTime) {
        try {
            AudioPerformer.getInstance().play();
        } catch (Exception e) {
            //Do not inform of missing file
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
        return false;
    }
}
