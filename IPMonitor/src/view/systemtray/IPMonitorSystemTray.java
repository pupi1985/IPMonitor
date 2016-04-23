/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package view.systemtray;

import java.awt.*;
import java.awt.TrayIcon.*;
import model.extras.*;
import model.configuration.*;
import view.systemtray.exceptions.*;

public class IPMonitorSystemTray {

    private static IPMonitorSystemTray instance;

    private IPMonitorSystemTray() {
        addIcons();
    }

    public static IPMonitorSystemTray getInstance() throws SystemTrayNotSupportedException {
        if ((!ConfigurationManager.getInstance().isService()) && (!ConfigurationManager.getInstance().getVisualConfigurationManager().isSystemTraySupported())) {
            throw new SystemTrayNotSupportedException();
        }
        if (instance == null) {
            instance = new IPMonitorSystemTray();
        }
        return instance;
    }

    public void displayMessage(String title, String text, MessageType messageType) {
        getIcon(0).displayMessage(title, text, messageType);
    }

    public TrayIcon getIcon(int index) {
        return SystemTray.getSystemTray().getTrayIcons()[index];
    }

    public void addIcons() {
        TrayIcon trayIcon = new TrayIcon(AboutInformation.getInstance().getImage(), "IP Monitor");
        trayIcon.setImageAutoSize(true);
        try {
            SystemTray.getSystemTray().add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void removeIcons() {
        TrayIcon[] trayIcons = SystemTray.getSystemTray().getTrayIcons();
        for (TrayIcon trayIcon : trayIcons) {
            SystemTray.getSystemTray().remove(trayIcon);
        }
    }
}
