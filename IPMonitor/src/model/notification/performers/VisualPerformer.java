/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.notification.performers;

import view.systemtray.*;
import model.extras.*;
import model.notification.configuration.*;
import view.systemtray.exceptions.*;

public class VisualPerformer extends AbstractPerformer {

    private static VisualPerformer instance;

    private VisualPerformer() {
    }

    public static VisualPerformer getInstance() {
        if (instance == null)
            instance = new VisualPerformer();
        return instance;
    }

    public void displayMessage(String fromIP, String toIP) {
        try {
            IPMonitorSystemTray.getInstance().displayMessage(
                    InfoParser.getInstance().parseField(
                    VisualConfiguration.getInstance().getTitle(), fromIP, toIP),
                    InfoParser.getInstance().parseField(
                    VisualConfiguration.getInstance().getText(), fromIP, toIP),
                    VisualConfiguration.getInstance().getIcon());
        } catch (SystemTrayNotSupportedException e) {
            //This is executed when running as a service or on a OS which does
            //not support the system tray
        }
    }
}
