/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.configuration;

import model.service.services.*;

public class ConfigurationManager {

    private static ConfigurationManager instance = new ConfigurationManager();
    private boolean service = false;
    private boolean autostart = false;
    private int osId = 0;
    private final String configurationFilePath = "files/ipmonitor.cfg";
    private final String lastCheckFilePath = "files/lastcheck.dat";
    private final String wrapperNativeDirectory = "files/wrapper/native/";
    private final String logFilesDirectory = "logs/";
    private final String logFileNameFormat = "yyyy-MM-dd";
    private final String ipPattern = "\\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b";
    private final int minCheckingInterval = 600;

    private ConfigurationManager() {
    }

    public static ConfigurationManager getInstance() {
        return instance;
    }

    public VisualConfigurationManager getVisualConfigurationManager() {
        if (!service) {
            return VisualConfigurationManager.getInstance();
        }
        return null;
    }

    public boolean isService() {
        return service;
    }

    public void setService(boolean service) {
        this.service = service;
    }

    public boolean isAutostart() {
        return autostart;
    }

    public void setAutostart(boolean autostart) {
        this.autostart = autostart;
    }

    public int getOsId() {
        return this.osId;
    }

    public void setOsId(int id) throws Exception {
        if (ServiceManager.getInstance().isValidIdForOsFamily(id)) {
            this.osId = id;
        } else {
            throw new Exception();
        }
    }

    public String getConfigurationFilePath() {
        return configurationFilePath;
    }

    public String getLastCheckFilePath() {
        return lastCheckFilePath;
    }

    public String getWrapperNativeDirectory() {
        return wrapperNativeDirectory;
    }

    public String getLogFilesDirectory() {
        return logFilesDirectory;
    }

    public String getLogFileNameFormat() {
        return logFileNameFormat;
    }

    public String getIPPattern() {
        return this.ipPattern;
    }

    public int getMinCheckingInterval() {
        return this.minCheckingInterval;
    }
}
