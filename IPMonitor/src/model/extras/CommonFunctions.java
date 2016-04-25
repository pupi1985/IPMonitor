/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.extras;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

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
        return DateFormat.getDateTimeInstance(DateFormat.SHORT,
                DateFormat.MEDIUM).format(date);
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
        new File(model.configuration.ConfigurationManager.getInstance().getLogFilesDirectoryPath()).mkdirs();
        MainLogger.getInstance().deleteOldFiles();
        ipMonitor.addIPMonitorListener(new IPMonitorEventLogger());
    }

    public boolean isValidIP(String ip) {
        return Pattern.matches(ConfigurationManager.getInstance().getIPPattern(), ip);
    }
}
