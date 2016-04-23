/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.extras;

import java.text.*;
import java.util.*;

public class InfoParser {

    private static InfoParser instance;
    private HashMap<IPMonitorParameters, String> parameters;

    public static InfoParser getInstance() {
        if (instance == null) {
            instance = new InfoParser();
        }
        return instance;
    }

    private InfoParser() {
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
        text = text.replaceAll(parameters.get(IPMonitorParameters.DATE), DateFormat.getDateInstance(
                DateFormat.MEDIUM).format(date));
        text = text.replaceAll(parameters.get(IPMonitorParameters.TIME), DateFormat.getTimeInstance(
                DateFormat.MEDIUM).format(date));
        return text;
    }

    public boolean isIPParameter(String parameter) {
        return parameter.equals(getParameter(IPMonitorParameters.NEW_IP)) ||
                parameter.equals(getParameter(IPMonitorParameters.OLD_IP));
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
