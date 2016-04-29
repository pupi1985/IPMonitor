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
