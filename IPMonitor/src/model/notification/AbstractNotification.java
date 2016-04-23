/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.notification;

import java.util.*;
import model.configuration.ConfigurationManager;
import model.ipmonitor.*;

public abstract class AbstractNotification implements IPMonitorListener {

    public void ipMonitorIPChange(String fromIP, String toIP, Date lastChecked, boolean firstTime) {
        if ((ConfigurationManager.getInstance().isService()) && (!this.canBeNotifiedWhenRunningAsService())) {
            return;
        }
        if (!firstTime) {
            this.ipMonitorIPChangeFiltered(fromIP, toIP, lastChecked, firstTime);
        }
    }

    public abstract void ipMonitorIPChangeFiltered(String fromIP, String toIP, Date lastChecked, boolean firstTime);

    public abstract boolean canBeNotifiedWhenRunningAsService();
}
