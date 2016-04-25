/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.configuration;

import java.io.File;
import java.net.URISyntaxException;

public class ConfigurationManager {

	private static ConfigurationManager instance = new ConfigurationManager();

	private final String basePath;

	private boolean service = false;
	private boolean autostart = false;
	private final String configurationFilePath;
	private final String lastCheckFilePath;
	private final String wrapperExecutableDirectoryPath;
	private final String wrapperScriptDirectoryPath;
	private final String logFilesDirectoryPath;
	private final String logFileNameFormat = "yyyy-MM-dd";
	private final String ipPattern = "\\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b";
	private final int minCheckingInterval = 600;

	private ConfigurationManager() {
		String basePath = "";
		try {
			basePath = ConfigurationManager.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		} catch (URISyntaxException e) {
		}
		this.basePath = new File(basePath).getParent();

		configurationFilePath = this.getPath("files/ipmonitor.cfg");
		lastCheckFilePath = this.getPath("files/lastcheck.dat");
		wrapperExecutableDirectoryPath = this.getPath("lib/jsw/bin/");
		wrapperScriptDirectoryPath = this.getPath("files/jsw/");
		logFilesDirectoryPath = this.getPath("logs/");
	}

	public static ConfigurationManager getInstance() {
		return instance;
	}

	public String getPath(String aPath) {
		return this.basePath + "/" + aPath;
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

	public String getConfigurationFilePath() {
		return configurationFilePath;
	}

	public String getLastCheckFilePath() {
		return lastCheckFilePath;
	}

	public String getWrapperExecutableDirectoryPath() {
		return wrapperExecutableDirectoryPath;
	}

	public String getWrapperScriptDirectoryPath() {
		return wrapperScriptDirectoryPath;
	}

	public String getLogFilesDirectoryPath() {
		return logFilesDirectoryPath;
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
