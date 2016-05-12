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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.UIManager;

import model.configuration.ConfigurationManager;
import model.ipmonitor.IPMonitor;
import model.logger.IPMonitorEventLogger;
import model.logger.MainLogger;

public class CommonFunctions {

    private static CommonFunctions instance = new CommonFunctions();
    private static final String SYSTEM_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private CommonFunctions() {
    }

    public static CommonFunctions getInstance() {
        return instance;
    }

    public String getFormattedDateTime(Date date) {
        return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(date);
    }

    public String getSystemFormattedDateTime(Date date) {
        try {
            return new SimpleDateFormat(SYSTEM_DATE_TIME_FORMAT).format(date);
        } catch (Exception e) {
            // If date is null
            return "";
        }
    }

    public Date getSystemDateTime(String dateAsString) throws ParseException {
        return new SimpleDateFormat(SYSTEM_DATE_TIME_FORMAT).parse(dateAsString);
    }

    public void postLoadProperties(IPMonitor ipMonitor) {
        new File(ConfigurationManager.getInstance().getLogFilesDirectoryPath()).mkdirs();
        MainLogger.getInstance().deleteOldFiles();
        ipMonitor.addIPMonitorListener(new IPMonitorEventLogger());
    }

    public boolean isValidIP(String ip) {
        return Pattern.matches(ConfigurationManager.getInstance().getIPPattern(), ip);
    }

    public String readStringFromInputStream(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append(System.lineSeparator());
        }
        return sb.toString();
    }

    public void loadLookAndFeel(String lookAndFeelClassName) throws Exception {
        UIManager.setLookAndFeel(lookAndFeelClassName);
        UIManager.getDefaults().put("TextArea.font", UIManager.getFont("TextField.font"));
    }
}
