/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.ipmonitor;

import model.observable.ObservableModelListener;

public interface IPMonitorExceptionListener extends ObservableModelListener {

	public void ipMonitorIPNotFound();

	public void ipMonitorIO();

}
