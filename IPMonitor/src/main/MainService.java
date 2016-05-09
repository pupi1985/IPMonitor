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

package main;

import org.tanukisoftware.wrapper.WrapperListener;
import org.tanukisoftware.wrapper.WrapperManager;

import model.configuration.ConfigurationManager;
import model.configuration.IPMonitorPropertiesManager;
import model.data.LastCheckPropertiesManager;
import model.data.LastCheckServiceListener;
import model.extras.CommonFunctions;
import model.ipmonitor.IPMonitor;

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
