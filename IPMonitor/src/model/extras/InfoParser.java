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

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

public class InfoParser {

    private HashMap<IPMonitorParameters, String> parameters;

    public InfoParser() {
        parameters = new HashMap<IPMonitorParameters, String>(IPMonitorParameters.values().length);
        parameters.put(IPMonitorParameters.OLD_IP, "%OLDIP%");
        parameters.put(IPMonitorParameters.NEW_IP, "%NEWIP%");
        parameters.put(IPMonitorParameters.DATE, "%DATE%");
        parameters.put(IPMonitorParameters.TIME, "%TIME%");
    }

    public String parseIPField(String text, String fromIP, String toIP) {
        text = text.replaceAll(parameters.get(IPMonitorParameters.OLD_IP), fromIP);
        text = text.replaceAll(parameters.get(IPMonitorParameters.NEW_IP), toIP);
        return text;
    }

    public String parseField(String text, String fromIP, String toIP) {
        text = parseIPField(text, fromIP, toIP);
        Date date = new Date();
        text = text.replaceAll(parameters.get(IPMonitorParameters.DATE),
                DateFormat.getDateInstance(DateFormat.MEDIUM).format(date));
        text = text.replaceAll(parameters.get(IPMonitorParameters.TIME),
                DateFormat.getTimeInstance(DateFormat.MEDIUM).format(date));
        return text;
    }

    public boolean isIPParameter(String parameter) {
        return parameter.equals(getParameter(IPMonitorParameters.NEW_IP))
                || parameter.equals(getParameter(IPMonitorParameters.OLD_IP));
    }

    public String getParameter(IPMonitorParameters parameter) {
        return parameters.get(parameter);
    }

    public enum IPMonitorParameters {
        OLD_IP,
        NEW_IP,
        DATE,
        TIME;
    }
}
