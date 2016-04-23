/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package main;

import javax.swing.*;
import controller.*;
import model.configuration.*;
import model.extras.*;
import model.ipmonitor.*;

public class MainApplication {

    public MainApplication() {
        IPMonitor ipMonitor = new IPMonitor();
        new IPMonitorPropertiesManager(ipMonitor).loadFromFile();
        CommonFunctions.getInstance().postLoadProperties(ipMonitor);
        loadLookAndFeel();
        new MainController(ipMonitor);
        this.postOpenWindow(ipMonitor);
    }

    private void loadLookAndFeel() {
        try {
            UIManager.setLookAndFeel(ConfigurationManager.getInstance().getVisualConfigurationManager().getLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void postOpenWindow(IPMonitor ipMonitor) {
        // If autocheck is enabled then start monitoring
        if (ConfigurationManager.getInstance().isAutostart()) {
            ipMonitor.start();
        }
    }
}
