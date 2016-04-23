/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package main;

import model.configuration.*;
import model.data.*;
import model.extras.*;
import model.ipmonitor.*;
import org.tanukisoftware.wrapper.*;

public class MainService {

    private IPMonitor ipMonitor;

    public MainService(String[] args) {
        WrapperManager.start(new WrapperListenerImpl(), args);
    }

    private class MyThread extends Thread {

        public void run() {
            ipMonitor.start();
        }
    }

    private class WrapperListenerImpl implements WrapperListener {

        public Integer start(String[] args) {
            ConfigurationManager.getInstance().setService(true);
            ipMonitor = new IPMonitor();
            new IPMonitorPropertiesManager(ipMonitor).loadFromFile();
            new LastCheckPropertiesManager(ipMonitor).loadFromFile();
            CommonFunctions.getInstance().postLoadProperties(ipMonitor);
            ipMonitor.addIPMonitorListener(new LastCheckServiceListener(ipMonitor));
            new MyThread().start();
            return null;
        }

        public void controlEvent(int event) {
            if (!WrapperManager.isControlledByNativeWrapper()) {
                // We are not being controlled by the Wrapper, so handle the event ourselves.
                if ((event == WrapperManager.WRAPPER_CTRL_C_EVENT) || (event == WrapperManager.WRAPPER_CTRL_CLOSE_EVENT) || (event == WrapperManager.WRAPPER_CTRL_SHUTDOWN_EVENT)) {
                    WrapperManager.stop(0);
                }
            }
            /* else {
             * The Wrapper will take care of this event
             * }
             * */
        }

        public int stop(int arg0) {
            ipMonitor.stop();
            return 0;
        }
    }
}
