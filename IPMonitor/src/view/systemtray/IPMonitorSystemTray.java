/*
 * IP Monitor is a simple application which monitors your public IP
 * address for changes and lets you set different kinds of notification
 * such as email, audio, pop up or executing a command. It can also run
 * in background as a Windows service or Linux/Mac daemon.
 *
 * Copyright (C) 2007 - 2016  Gabriel Zanetti http://github.com/pupi1985
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package view.systemtray;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;

import model.configuration.ConfigurationManager;
import model.extras.AboutInformation;
import view.systemtray.exceptions.SystemTrayNotSupportedException;

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
        }
    }

    public void removeIcons() {
        TrayIcon[] trayIcons = SystemTray.getSystemTray().getTrayIcons();
        for (TrayIcon trayIcon : trayIcons) {
            SystemTray.getSystemTray().remove(trayIcon);
        }
    }
}
