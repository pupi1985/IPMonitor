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

package model.extras;

import java.awt.Image;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AboutInformation {

    public Image getImage() {
        return Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/tipmonitor64.png"));
    }

    public String getDeveloper() {
        return "Gabriel Zanetti";
    }

    public String getName() {
        return "IP Monitor";
    }

    public String getVersion() {
        return "0.04";
    }

    public Date getDate() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse("2010-03-27");
        } catch (Exception e) {
            return null;
        }
    }

    public String getUrl() {
        return "http://www.ip-monitor.com.ar";
    }

    public String getVisualUrl() {
        return "www.ip-monitor.com.ar";
    }
}
