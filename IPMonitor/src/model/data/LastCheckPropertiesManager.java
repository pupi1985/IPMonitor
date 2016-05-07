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

package model.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import model.configuration.ConfigurationManager;
import model.extras.CommonFunctions;
import model.ipmonitor.IPMonitor;

public class LastCheckPropertiesManager {

    private IPMonitor ipMonitor;

    public LastCheckPropertiesManager(IPMonitor ipMonitor) {
        this.ipMonitor = ipMonitor;
    }

    public void loadFromFile() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(ConfigurationManager.getInstance().getLastCheckFilePath())));
        } catch (Exception e) {
            //This is executed if the file doesn't exist
        }
        loadLastIP(properties);
    }

    public void saveToFile() {
        Properties properties = new Properties();
        saveLastIP(properties);
        saveLastChecked(properties);
        saveLastChanged(properties);
        try {
            properties.store(new FileOutputStream(new File(ConfigurationManager.getInstance().getLastCheckFilePath())),
                    "This file is only used when IP Monitor is run as a service");
        } catch (Exception e) {
        }
    }

    private void loadLastIP(Properties properties) {
        try {
            this.ipMonitor.setLastIP(String.valueOf(properties.getProperty(LastCheckProperties.LAST_IP, LastCheckProperties.LAST_IP_VALUE)));
        } catch (Exception e) {
            //If some has manually changed the text in the properties file to
            //a non-valid IP address then just don't load it
        }
    }

    private void saveLastIP(Properties properties) {
        properties.setProperty(LastCheckProperties.LAST_IP, String.valueOf(this.ipMonitor.getLastIP()));
    }

    private void saveLastChecked(Properties properties) {
        properties.setProperty(LastCheckProperties.LAST_CHECKED,
                CommonFunctions.getInstance().getSystemFormattedDateTime(this.ipMonitor.getLastChecked()));
    }

    private void saveLastChanged(Properties properties) {
        properties.setProperty(LastCheckProperties.LAST_CHANGE,
                CommonFunctions.getInstance().getSystemFormattedDateTime(this.ipMonitor.getLastChange()));
    }
}
