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

package model.configuration;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.SystemTray;

public class VisualConfigurationManager {

    private static VisualConfigurationManager instance = null;
    private boolean isSystemTraySupported;
    private Point mainViewLocation = null;
    private Dimension mainViewSize = null;
    private boolean mainViewLastVisibleState = true;
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

    public boolean getMainViewLastVisibleState() {
        return mainViewLastVisibleState;
    }

    public void setMainViewLastVisibleState(boolean visible) {
        mainViewLastVisibleState = visible;
    }

    public String getLookAndFeelClassName() {
        return lookAndFeelClassName;
    }

    public void setLookAndFeelClassName(String lookAndFeelClassName) {
        this.lookAndFeelClassName = lookAndFeelClassName;
    }
}
