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

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Locale;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import controller.extras.LookAndFeelInfoWrapper;
import controller.extras.TimeUnitConverter;
import controller.options.AudioConfigurationController;
import controller.options.CommandConfigurationController;
import controller.options.MailConfigurationController;
import controller.options.MailTestController;
import controller.options.VisualConfigurationController;
import model.configuration.ConfigurationManager;
import model.configuration.IPMonitorProperties;
import model.configuration.IPMonitorPropertiesManager;
import model.ipmonitor.IPMonitor;
import model.ipmonitor.exceptions.InvalidIntervalException;
import model.logger.MainLogger;
import model.logger.exceptions.InvalidMaxDaysToKeepLogs;
import model.notification.AudioNotification;
import model.notification.CommandNotification;
import model.notification.MailNotification;
import model.notification.VisualNotification;
import model.notification.configuration.AudioConfiguration;
import model.notification.configuration.CommandConfiguration;
import model.notification.performers.AudioPerformer;
import model.notification.performers.CommandPerformer;
import model.notification.performers.MailPerformer;
import model.notification.performers.VisualPerformer;
import model.service.ServiceManager;
import model.service.helpers.ProcessResult;
import view.OptionsView;

public class OptionsController {

    private IPMonitor ipMonitor;
    private OptionsView optionsView;
    private String serviceName;

    public OptionsController(JFrame owner, IPMonitor ipMonitor) {
        this.ipMonitor = ipMonitor;

        ActionListener cancelAction = new CancelAction();

        optionsView = new OptionsView(owner, ipMonitor);
        optionsView.getJButtonOk().addActionListener(new JButtonOkAction());
        optionsView.getJButtonCancel().addActionListener(cancelAction);
        optionsView.getJButtonApply().addActionListener(new JButtonApplyAction());

        optionsView.getJPanelOptionsNotification().getJButtonAudioConfiguration()
                .addActionListener(new JButtonAudioConfiguration());
        optionsView.getJPanelOptionsNotification().getJButtonAudioTest().addActionListener(new JButtonAudioTest());
        optionsView.getJPanelOptionsNotification().getJButtonMailConfiguration()
                .addActionListener(new JButtonMailConfiguration());
        optionsView.getJPanelOptionsNotification().getJButtonMailTest().addActionListener(new JButtonMailTest());
        optionsView.getJPanelOptionsNotification().getJButtonVisualConfiguration()
                .addActionListener(new JButtonVisualConfiguration());
        optionsView.getJPanelOptionsNotification().getJButtonVisualTest().addActionListener(new JButtonVisualTest());
        optionsView.getJPanelOptionsNotification().getJButtonCommandConfiguration()
                .addActionListener(new JButtonCommandConfiguration());
        optionsView.getJPanelOptionsNotification().getJButtonCommandTest().addActionListener(new JButtonCommandTest());

        serviceName = ServiceManager.getInstance().getService().getServiceName().toLowerCase(Locale.ENGLISH);
        optionsView.getJPanelOptionsService().getJButtonInstall().addActionListener(new JButtonInstallServiceAction());
        optionsView.getJPanelOptionsService().getJButtonUninstall()
                .addActionListener(new JButtonUninstallServiceAction());
        optionsView.getJPanelOptionsService().getJButtonStart().addActionListener(new JButtonStartServiceAction());
        optionsView.getJPanelOptionsService().getJButtonStop().addActionListener(new JButtonStopServiceAction());
        optionsView.getJPanelOptionsService().getJButtonTest().addActionListener(new JButtonTestServiceAction());

        optionsView.getJPanelOptionsLogging().getJCheckBoxEnableLogging()
                .addActionListener(new JCheckBoxEnableLoggingAction());
        optionsView.getRootPane().registerKeyboardAction(cancelAction, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
        setEnableLogging();
        optionsView.setVisible(true);
    }

    private void setInterval() throws NumberFormatException, InvalidIntervalException {
        try {
            int hours = Integer.valueOf(optionsView.getJPanelOptionsMonitor().getJTextFieldHours().getText());
            int minutes = Integer.valueOf(optionsView.getJPanelOptionsMonitor().getJTextFieldMinutes().getText());
            int seconds = Integer.valueOf(optionsView.getJPanelOptionsMonitor().getJTextFieldSeconds().getText());
            if (hours < 0 || (minutes < 0 || minutes > 59) || (seconds < 0 || seconds > 59)) {
                throw new NumberFormatException();
            }
            ipMonitor.setInterval(hours * TimeUnitConverter.HOURS + minutes * TimeUnitConverter.MINUTES + seconds);
        } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null, "Interval is not correct. Please enter a valid interval.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            optionsView.getJPanelOptionsMonitor().getJTextFieldHours().requestFocus();
            throw e1;
        } catch (InvalidIntervalException e2) {
            JOptionPane.showMessageDialog(null, "Interval is not correct. It can not be less than 10 minutes.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw e2;
        }
    }

    private void setAutoStart() {
        ConfigurationManager.getInstance()
                .setAutostart(optionsView.getJPanelOptionsMonitor().getJCheckBoxAutoStart().isSelected());
    }

    private void setUrl() throws MalformedURLException {
        try {
            ipMonitor.setUrl(optionsView.getJPanelOptionsMonitor().getJTextFieldURL().getText());
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null,
                    "The URL is not correct. Use a sintax similar to:\nhttp://www.server.com", "Error",
                    JOptionPane.ERROR_MESSAGE);
            optionsView.getJPanelOptionsMonitor().getJTextFieldURL().requestFocus();
            throw e;
        }
    }

    private void setAudioNotification() {
        if (optionsView.getJPanelOptionsNotification().getJCheckBoxEnableAudioNotification().isSelected()) {
            ipMonitor.addIPMonitorListener(AudioNotification.getInstance());
        } else {
            ipMonitor.removeIPMonitorListener(AudioNotification.getInstance());
        }
    }

    private void setMailNotification() {
        if (optionsView.getJPanelOptionsNotification().getJCheckBoxEnableMailNotification().isSelected()) {
            ipMonitor.addIPMonitorListener(MailNotification.getInstance());
        } else {
            ipMonitor.removeIPMonitorListener(MailNotification.getInstance());
        }
    }

    private void setVisualNotification() {
        if (optionsView.getJPanelOptionsNotification().getJCheckBoxEnableVisualNotification().isSelected()) {
            ipMonitor.addIPMonitorListener(VisualNotification.getInstance());
        } else {
            ipMonitor.removeIPMonitorListener(VisualNotification.getInstance());
        }
    }

    private void setCommandNotification() {
        if (optionsView.getJPanelOptionsNotification().getJCheckBoxEnableCommandNotification().isSelected()) {
            ipMonitor.addIPMonitorListener(CommandNotification.getInstance());
        } else {
            ipMonitor.removeIPMonitorListener(CommandNotification.getInstance());
        }
    }

    private void setLookAndFeel() {
        try {
            LookAndFeelInfoWrapper lookAndFeelInfoWrapper = ((LookAndFeelInfoWrapper) optionsView
                    .getJPanelOptionsInterface().getJListLookAndFeel().getSelectedValue());
            if (UIManager.getLookAndFeel().getName().equals(lookAndFeelInfoWrapper.getName())) {
                return;
            }
            try {
                ConfigurationManager.getInstance().getVisualConfigurationManager()
                        .setLookAndFeelClassName(lookAndFeelInfoWrapper.getClassName());
                UIManager.setLookAndFeel(
                        ConfigurationManager.getInstance().getVisualConfigurationManager().getLookAndFeelClassName());
                SwingUtilities.updateComponentTreeUI(optionsView.getOwner());
                SwingUtilities.updateComponentTreeUI(optionsView);
                optionsView.pack();
                optionsView.getOwner().pack();
            } catch (Exception e1) {
            }
        } catch (Exception e2) {
        }
    }

    private void setEnableLogging() {
        boolean enabled = optionsView.getJPanelOptionsLogging().getJCheckBoxEnableLogging().isSelected();
        JPanel loggingConfigurationPanel = optionsView.getJPanelOptionsLogging().getJPanelLoggingConfiguration();
        Component[] components = loggingConfigurationPanel.getComponents();
        loggingConfigurationPanel.setEnabled(enabled);
        for (Component component : components) {
            component.setEnabled(enabled);
        }
    }

    private void setLogging() {
        MainLogger.getInstance()
                .setEnabled(optionsView.getJPanelOptionsLogging().getJCheckBoxEnableLogging().isSelected());
    }

    private void setMaxDaysToKeepLogs() throws NumberFormatException, InvalidMaxDaysToKeepLogs {
        int days;
        try {
            days = Integer.valueOf(optionsView.getJPanelOptionsLogging().getJTextFieldDaysToKeepLogs().getText());
            MainLogger.getInstance().setMaxDaysToKeepLogs(days);
        } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null,
                    "The number of days must be a positive integer. Please enter a valid number of days.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            optionsView.getJPanelOptionsLogging().getJTextFieldDaysToKeepLogs().requestFocus();
            throw e1;
        } catch (InvalidMaxDaysToKeepLogs e2) {
            if (MainLogger.getInstance().isEnabled()) {
                JOptionPane.showMessageDialog(null,
                        "The number of days must be a positive integer. Please enter a valid number of days.\n"
                                + "If you don't want to keep any log you should disable logging.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                optionsView.getJPanelOptionsLogging().getJTextFieldDaysToKeepLogs().requestFocus();
                throw e2;
            } else {
                MainLogger.getInstance()
                        .setMaxDaysToKeepLogs(IPMonitorProperties.OPTIONS_MONITOR_MAX_DAYS_TO_KEEP_LOGS_VALUE);
            }
        }
    }

    private void saveSettings() {
        MainLogger.getInstance().deleteOldFiles();
        new IPMonitorPropertiesManager(ipMonitor).saveToFile();
        try {
            if (ServiceManager.getInstance().getService().isRunning()) {
                int answer = JOptionPane.showConfirmDialog(null,
                        "The IP Monitor service is currently running. In order to apply the current settings\n"
                                + "the service must be restarted. Do you want to restart the service now?",
                        "Service restart confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    ServiceManager.getInstance().getService().stop();
                    ServiceManager.getInstance().getService().start();
                }
            }
        } catch (Exception e) {
        }
    }

    private String getOldIP() {
        return ipMonitor.getLastIP().equals("") ? "[OLD_IP_HERE]" : ipMonitor.getLastIP();
    }

    private String getNewIP() {
        return "[NEW_IP_HERE]";
    }

    private class JButtonAudioConfiguration implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            new AudioConfigurationController(optionsView);
        }
    }

    private class JButtonAudioTest implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            try {
                AudioPerformer.getInstance().play();
            } catch (Exception e) {
                JOptionPane
                        .showMessageDialog(null,
                                "An error has been detected while opening file\n"
                                        + AudioConfiguration.getInstance().getFileName(),
                                "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class JButtonMailConfiguration implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            new MailConfigurationController(optionsView);
        }
    }

    private class JButtonMailTest implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            JOptionPane.showMessageDialog(null,
                    "This test might take a few minutes depending on network" + System.lineSeparator()
                            + "congestion and email notification configuration." + System.lineSeparator()
                            + System.lineSeparator() + "Close this dialog to start the test.",
                    "Please wait", JOptionPane.INFORMATION_MESSAGE);
            MailPerformer mailPerformer = new MailPerformer();
            String result = System.lineSeparator() + System.lineSeparator();
            try {
                mailPerformer.sendMail(getOldIP(), getNewIP());
                result += "Email has been successfully delivered";
            } catch (Exception e) {
                result += "An error has been detected while trying to send the email." + System.lineSeparator()
                        + "Check the network settings and the email notification configuration.";
            } finally {
                new MailTestController(optionsView, mailPerformer.getLastMailDebugInformation() + result);
            }
        }
    }

    private class JButtonVisualConfiguration implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            new VisualConfigurationController(optionsView);
        }
    }

    private class JButtonVisualTest implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            VisualPerformer.getInstance().displayMessage(getOldIP(), getNewIP());
        }
    }

    private class JButtonCommandConfiguration implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            new CommandConfigurationController(optionsView);
        }
    }

    private class JButtonCommandTest implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            try {
                CommandPerformer.getInstance().executeCommand(getOldIP(), getNewIP());
            } catch (Exception e) {
                JOptionPane
                        .showMessageDialog(null,
                                "An error has been detected while executing command\n"
                                        + CommandConfiguration.getInstance().getCommand(),
                                "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private abstract class AbstractJButtonServiceAction implements ActionListener {

        private String title;
        private String errorMessage;

        public AbstractJButtonServiceAction(String title, String errorMessage) {
            this.title = title;
            this.errorMessage = errorMessage;
        }

        public abstract ProcessResult serviceOperation() throws IOException;

        public void actionPerformed(ActionEvent event) {
            try {
                ProcessResult processResult = serviceOperation();
                StringBuffer output = new StringBuffer(processResult.getOutput().trim());
                if (ServiceManager.getInstance().getService().shouldIncludeExitCode()) {
                    output.append(System.lineSeparator());
                    output.append(System.lineSeparator());
                    output.append("Exit code: ");
                    output.append(processResult.getExitCode());
                }
                JOptionPane.showMessageDialog(null, output.toString(), this.title, JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, this.errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class JButtonInstallServiceAction extends AbstractJButtonServiceAction {

        public JButtonInstallServiceAction() {
            super("Installation result", "There has been an error while installing the " + serviceName + ".");
        }

        public ProcessResult serviceOperation() throws IOException {
            return ServiceManager.getInstance().getService().install();
        }
    }

    private class JButtonUninstallServiceAction extends AbstractJButtonServiceAction {

        public JButtonUninstallServiceAction() {
            super("Uninstallation result", "There has been an error while uninstalling the " + serviceName + ".");
        }

        public ProcessResult serviceOperation() throws IOException {
            return ServiceManager.getInstance().getService().uninstall();
        }
    }

    private class JButtonStartServiceAction extends AbstractJButtonServiceAction {

        public JButtonStartServiceAction() {
            super("Start result", "There has been an error while starting the " + serviceName + ".");
        }

        public ProcessResult serviceOperation() throws IOException {
            return ServiceManager.getInstance().getService().start();
        }
    }

    private class JButtonStopServiceAction extends AbstractJButtonServiceAction {

        public JButtonStopServiceAction() {
            super("Stop result", "There has been an error while stopping the " + serviceName + ".");
        }

        public ProcessResult serviceOperation() throws IOException {
            return ServiceManager.getInstance().getService().stop();
        }
    }

    private class JButtonTestServiceAction extends AbstractJButtonServiceAction {

        public JButtonTestServiceAction() {
            super("Test result", "There has been an error while testing the state of the " + serviceName + ".");
        }

        public ProcessResult serviceOperation() throws IOException {
            return ServiceManager.getInstance().getService().status();
        }
    }

    private class JCheckBoxEnableLoggingAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            setEnableLogging();
        }
    }

    private class JButtonOkAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            JButtonApplyAction applyAction = new JButtonApplyAction();
            applyAction.actionPerformed(null);
            if (applyAction.isEverythingOk()) {
                optionsView.dispose();
            }
        }
    }

    private class CancelAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            optionsView.dispose();
        }
    }

    private class JButtonApplyAction implements ActionListener {

        private boolean isEveryhingOk;

        public void actionPerformed(ActionEvent event) {
            isEveryhingOk = true;
            try {
                setInterval();
            } catch (Exception e) {
                isEveryhingOk = false;
            }
            setAutoStart();
            try {
                setUrl();
            } catch (Exception e) {
                isEveryhingOk = false;
            }
            setAudioNotification();
            setMailNotification();
            setVisualNotification();
            setCommandNotification();
            setLookAndFeel();
            setLogging();
            try {
                setMaxDaysToKeepLogs();
            } catch (Exception e) {
                isEveryhingOk = false;
            }
            saveSettings();
        }

        public boolean isEverythingOk() {
            return isEveryhingOk;
        }
    }
}
