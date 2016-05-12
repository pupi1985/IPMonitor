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

package main;

import controller.MainController;
import model.configuration.ConfigurationManager;
import model.configuration.IPMonitorPropertiesManager;
import model.extras.CommonFunctions;
import model.extras.OperativeSystemGuesser;
import model.ipmonitor.IPMonitor;

public class MainApplication {

    public MainApplication() {
        applyOperativeSystemCustomConfigurations();
        IPMonitor ipMonitor = new IPMonitor();
        new IPMonitorPropertiesManager(ipMonitor).loadFromFile();
        CommonFunctions.getInstance().postLoadProperties(ipMonitor);
        loadLookAndFeel();
        new MainController(ipMonitor);
        this.postOpenWindow(ipMonitor);
    }

    private void applyOperativeSystemCustomConfigurations() {
        if (OperativeSystemGuesser.isMac()) {
            // Remove the Java's coffee cup from the Dock
            System.setProperty("apple.awt.UIElement", "true");
        }
    }

    private void loadLookAndFeel() {
        try {
            CommonFunctions.getInstance().loadLookAndFeel(
                    ConfigurationManager.getInstance().getVisualConfigurationManager().getLookAndFeelClassName());
        } catch (Exception e) {
        }
    }

    private void postOpenWindow(IPMonitor ipMonitor) {
        // If autocheck is enabled then start monitoring
        if (ConfigurationManager.getInstance().isAutostart()) {
            ipMonitor.start();
        }
    }
}
