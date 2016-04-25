/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.service;

public class ServiceManager {

	private static ServiceManager instance;

	private AbstractService service;

	private ServiceManager() {
		this.service = isOSFamilyWindows() ? new WindowsBasedService() : new UnixBasedService();
	}

	public static ServiceManager getInstance() {
		if (instance == null) {
			instance = new ServiceManager();
		}
		return instance;
	}

	public AbstractService getService() {
		return this.service;
	}

	private boolean isOSFamilyWindows() {
		return System.getProperty("os.name").toLowerCase().contains("windows");
	}

}
