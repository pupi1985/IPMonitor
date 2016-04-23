/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.ipmonitor;

import java.util.*;
import model.observable.*;
import model.*;

public interface IPMonitorListener extends ObservableModelListener {

	public void ipMonitorIPChange(String fromIP, String toIP, Date lastChecked, boolean firstTime);

	public void ipMonitorStart();

	public void ipMonitorStop();

	public void ipMonitorIntervalChange();

	public void ipMonitorIPCheckStart();

	public void ipMonitorIPCheckEnd();

}
