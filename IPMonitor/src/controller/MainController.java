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

package controller;

import java.awt.*;
import java.awt.event.*;
import controller.extras.*;
import java.util.*;
import javax.swing.*;
import model.extras.*;
import view.*;
import view.systemtray.*;
import view.systemtray.exceptions.*;
import model.configuration.*;
import model.ipmonitor.*;

public class MainController {

    private IPMonitor ipMonitor;
    private MainView mainView;
    private CheckIPAction checkIPAction;

    public MainController(IPMonitor ipMonitor) {
        this.ipMonitor = ipMonitor;
        ipMonitor.addIPMonitorExceptionListener(new IPMonitorExceptionListenerImpl());
        IPMonitorListenerImpl iPMonitorListenerImpl = new IPMonitorListenerImpl();
        ipMonitor.addIPMonitorListener(iPMonitorListenerImpl);
        mainView = new MainView(ipMonitor);
        mainView.addWindowListener(new WindowListenerImpl());

        AboutAction aboutAction = new AboutAction("About...");
        mainView.getJMenuItemHelpAbout().setAction(aboutAction);

        checkIPAction = new CheckIPAction("Check IP");
        mainView.getJButtonCheckIP().setAction(checkIPAction);

        StartStopAction startStopAction = new StartStopAction("Start");
        mainView.getJButtonStartStop().setAction(startStopAction);

        OptionsAction optionsAction = new OptionsAction("Options...");
        mainView.getJMenuItemFileOptions().setAction(optionsAction);

        ExitAction exitAction = new ExitAction("Exit");
        mainView.getJMenuItemFileExit().setAction(exitAction);

        try {
            TrayIcon trayIcon = IPMonitorSystemTray.getInstance().getIcon(0);
            trayIcon.setPopupMenu(mainView.getPopupMenu());
            trayIcon.addMouseListener(new TrayIconMouseListener());
            mainView.getMenuItemCheckIP().addActionListener(checkIPAction);
            mainView.getMenuItemStartStop().addActionListener(startStopAction);
            mainView.getMenuItemOptions().addActionListener(optionsAction);
            mainView.getMenuItemExit().addActionListener(exitAction);
        } catch (SystemTrayNotSupportedException ex) {
        }
        loadFromFile();
        if (ipMonitor.isChecking()) {
            iPMonitorListenerImpl.ipMonitorStart();
        }
        mainView.setVisible(true);

    }

    private void loadFromFile() {
        if (ConfigurationManager.getInstance().getVisualConfigurationManager().getMainViewLocation() == null) {
            mainView.setLocationRelativeTo(null);
        } else {
            mainView.setLocation(ConfigurationManager.getInstance().getVisualConfigurationManager().getMainViewLocation());
        }
        if (ConfigurationManager.getInstance().getVisualConfigurationManager().getMainViewSize() != null) {
            mainView.setSize(ConfigurationManager.getInstance().getVisualConfigurationManager().getMainViewSize());
        }
    }

    private void saveToFile() {
        ConfigurationManager.getInstance().getVisualConfigurationManager().setMainViewLocation(
                mainView.getLocation());
        ConfigurationManager.getInstance().getVisualConfigurationManager().setMainViewSize(
                mainView.getSize());
        new IPMonitorPropertiesManager(ipMonitor).saveToFile();
    }

    private class IPMonitorListenerImpl implements IPMonitorListener {

        public void ipMonitorIPCheckStart() {
            checkIPAction.setEnabled(false);
            mainView.getJLabelLastCheckedField().setText(
                    CommonFunctions.getInstance().getFormattedDateTime(ipMonitor.getLastChecked()));
        }

        public void ipMonitorIPCheckEnd() {
            checkIPAction.setEnabled(true);
        }

        public void ipMonitorIntervalChange() {
            mainView.getJLabelIntervalField().setText(
                    new TimeUnitConverter(ipMonitor.getInterval()).toString());
        }

        public void ipMonitorStart() {
            String stop = "Stop";
            mainView.getJButtonStartStop().setText(stop);
            mainView.getMenuItemStartStop().setLabel(stop);
            mainView.getJLabelStatusField().setForeground(new Color(0, 128, 0));
            mainView.getJLabelStatusField().setText("On");
        }

        public void ipMonitorStop() {
            String start = "Start";
            mainView.getJButtonStartStop().setText(start);
            mainView.getMenuItemStartStop().setLabel(start);
            mainView.getJLabelStatusField().setForeground(new Color(192, 0, 0));
            mainView.getJLabelStatusField().setText("Off");
        }

        public void ipMonitorIPChange(String fromIP, String toIP, Date lastChecked, boolean firstTime) {
            SwingUtilities.invokeLater(new IPChangeThread(toIP, lastChecked, firstTime));
        }
    }

    private class IPMonitorExceptionListenerImpl implements
            IPMonitorExceptionListener {

        public void ipMonitorIO() {
            SwingUtilities.invokeLater(new Runnable() {

                public void run() {
                    mainView.getJLabelCurrentIPField().setText("URL error");
                }
            });
        }

        public void ipMonitorIPNotFound() {
            SwingUtilities.invokeLater(new Runnable() {

                public void run() {
                    mainView.getJLabelCurrentIPField().setText("IP missing in URL");
                }
            });
        }
    }

    private class WindowListenerImpl implements WindowListener {

        public void windowActivated(WindowEvent event) {
        }

        public void windowClosed(WindowEvent event) {
            saveToFile();
        }

        public void windowClosing(WindowEvent event) {
            saveToFile();
        }

        public void windowDeactivated(WindowEvent event) {
        }

        public void windowDeiconified(WindowEvent event) {
        }

        public void windowIconified(WindowEvent event) {
            if (!ConfigurationManager.getInstance().getVisualConfigurationManager().isSystemTraySupported()) {
                return;
            }
        }

        public void windowOpened(WindowEvent event) {
        }
    }

    private class TrayIconMouseListener implements ActionListener,
            MouseListener {

        public void actionPerformed(ActionEvent event) {
        }

        public void mouseClicked(MouseEvent event) {
            if (event.getButton() != MouseEvent.BUTTON1) {
                return;
            }
            if (mainView.getState() != JFrame.ICONIFIED) {
                mainView.setVisible(false);
                mainView.setState(JFrame.ICONIFIED);
            } else {
                mainView.setVisible(true);
                mainView.setState(JFrame.NORMAL);
                mainView.toFront();
            }
        }

        public void mouseEntered(MouseEvent event) {
        }

        public void mouseExited(MouseEvent event) {
        }

        public void mousePressed(MouseEvent event) {
        }

        public void mouseReleased(MouseEvent event) {
        }
    }

    private abstract class AbstractMnemonicAction extends AbstractAction {

        public AbstractMnemonicAction(String text) {
            putValue(NAME, text);
            putValue(MNEMONIC_KEY, KeyStroke.getKeyStroke(text.substring(0, 1).toUpperCase()).getKeyCode());
        }
    }

    private class AboutAction extends AbstractMnemonicAction {

        public AboutAction(String text) {
            super(text);
        }

        public void actionPerformed(ActionEvent event) {
            new AboutController(mainView);
        }
    }

    private class CheckIPAction extends AbstractMnemonicAction {

        public CheckIPAction(String text) {
            super(text);
        }

        public void actionPerformed(ActionEvent event) {
            ipMonitor.beginCheckingIP();
        }
    }

    private class StartStopAction extends AbstractMnemonicAction {

        public StartStopAction(String text) {
            super(text);
        }

        public void actionPerformed(ActionEvent event) {
            if (ipMonitor.isChecking()) {
                ipMonitor.stop();
            } else {
                ipMonitor.start();
            }
        }
    }

    private class OptionsAction extends AbstractMnemonicAction {

        public OptionsAction(String text) {
            super(text);
        }

        public void actionPerformed(ActionEvent event) {
            new OptionsController(mainView, ipMonitor);
        }
    }

    private class ExitAction extends AbstractMnemonicAction {

        public ExitAction(String text) {
            super(text);
        }

        public void actionPerformed(ActionEvent event) {
            mainView.dispose();
            try {
                IPMonitorSystemTray.getInstance().removeIcons();
            } catch (SystemTrayNotSupportedException ex) {
            }
        }
    }

    private class IPChangeThread extends Thread {

        private String toIP;
        private boolean firstTime;
        private Date lastChange;

        public IPChangeThread(String toIP, Date lastChecked, boolean firstTime) {
            this.toIP = toIP;
            this.firstTime = firstTime;
            this.lastChange = lastChecked;
        }

        public void run() {
            mainView.getJLabelCurrentIPField().setText(toIP);
            if (!firstTime) {
                mainView.getJLabelLastChangeField().setText(
                        CommonFunctions.getInstance().getFormattedDateTime(this.lastChange));
            }
        }
    }
}
