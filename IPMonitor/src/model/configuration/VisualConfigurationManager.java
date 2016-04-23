/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.configuration;

import java.awt.*;

public class VisualConfigurationManager {

    private static VisualConfigurationManager instance = null;
    private boolean isSystemTraySupported;
    private Point mainViewLocation = null;
    private Dimension mainViewSize = null;
    private String lookAndFeelClassName = null;

    private VisualConfigurationManager() {
        this.isSystemTraySupported = SystemTray.isSupported() && !ConfigurationManager.getInstance().isService();
    }

    public static VisualConfigurationManager getInstance() {
        if (instance == null) {
            instance = new VisualConfigurationManager();
        }
        return instance;
    }

    public boolean isSystemTraySupported() {
        return isSystemTraySupported;
    }

    public Point getMainViewLocation() {
        return mainViewLocation;
    }

    public void setMainViewLocation(Point point) {
        this.mainViewLocation = point;
    }

    public Dimension getMainViewSize() {
        return mainViewSize;
    }

    public void setMainViewSize(Dimension dimension) {
        this.mainViewSize = dimension;
    }

    public String getLookAndFeelClassName() {
        return lookAndFeelClassName;
    }

    public void setLookAndFeelClassName(String lookAndFeelClassName) {
        this.lookAndFeelClassName = lookAndFeelClassName;
    }

}
