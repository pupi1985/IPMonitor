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

package model.logger;

import java.util.Date;

import model.ipmonitor.IPMonitorExceptionListener;
import model.ipmonitor.IPMonitorListener;

public class IPMonitorEventLogger implements IPMonitorListener, IPMonitorExceptionListener {

    public void ipMonitorIPChange(String fromIP, String toIP, Date lastChecked, boolean firstTime) {
        if (firstTime) {
            MainLogger.getInstance().write("Fetched initial IP " + toIP);
        } else {
            MainLogger.getInstance().write("IP changed from " + fromIP + " to " + toIP);
        }
    }

    public void ipMonitorStart() {
        MainLogger.getInstance().write("Started monitoring");
    }

    public void ipMonitorStop() {
        MainLogger.getInstance().write("Stopped monitoring");
    }

    public void ipMonitorIntervalChange() {
    }

    public void ipMonitorIPCheckStart() {
    }

    public void ipMonitorIPCheckEnd() {
    }

    public void ipMonitorIPNotFound() {
        MainLogger.getInstance().write("IP not found in WEB page");
    }

    public void ipMonitorTimeout() {
        MainLogger.getInstance().write("Connection to remote host timed out");
    }

    public void ipMonitorIO() {
        MainLogger.getInstance().write("Could not find WEB page");
    }
}
