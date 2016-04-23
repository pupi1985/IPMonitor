/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JButton;
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
import model.service.exceptions.OSNotSupportedException;
import model.service.os.OSManager;
import model.service.services.AbstractService;
import model.service.services.ServiceManager;
import view.OptionsView;

public class OptionsController {

    private IPMonitor ipMonitor;
    private OptionsView optionsView;
    private String serviceName;
    private boolean serviceOK = true;

    public OptionsController(JFrame owner, IPMonitor ipMonitor) {
        this.ipMonitor = ipMonitor;
        optionsView = new OptionsView(owner, ipMonitor);
        optionsView.getJButtonOk().addActionListener(new JButtonOkAction());
        optionsView.getJButtonCancel().addActionListener(
                new JButtonCancelAction());
        optionsView.getJButtonApply().addActionListener(
                new JButtonApplyAction());

        optionsView.getJPanelOptionsNotification().getJButtonAudioConfiguration().addActionListener(
                new JButtonAudioConfiguration());
        optionsView.getJPanelOptionsNotification().getJButtonAudioTest().addActionListener(
                new JButtonAudioTest());
        optionsView.getJPanelOptionsNotification().getJButtonMailConfiguration().addActionListener(
                new JButtonMailConfiguration());
        optionsView.getJPanelOptionsNotification().getJButtonMailTest().addActionListener(
                new JButtonMailTest());
        optionsView.getJPanelOptionsNotification().getJButtonVisualConfiguration().addActionListener(
                new JButtonVisualConfiguration());
        optionsView.getJPanelOptionsNotification().getJButtonVisualTest().addActionListener(
                new JButtonVisualTest());
        optionsView.getJPanelOptionsNotification().getJButtonCommandConfiguration().addActionListener(
                new JButtonCommandConfiguration());
        optionsView.getJPanelOptionsNotification().getJButtonCommandTest().addActionListener(
                new JButtonCommandTest());

        if (ServiceManager.getInstance().isOSSupported()) {
            AbstractService genericService;
            try {
                genericService = ServiceManager.getInstance().getService();
                serviceName = genericService.getServiceName().toLowerCase();
                optionsView.getJPanelOptionsService().getJButtonInstall().addActionListener(
                        new JButtonInstallServiceAction());
                optionsView.getJPanelOptionsService().getJButtonUninstall().addActionListener(
                        new JButtonUninstallServiceAction());
                optionsView.getJPanelOptionsService().getJButtonStart().addActionListener(
                        new JButtonStartServiceAction());
                optionsView.getJPanelOptionsService().getJButtonStop().addActionListener(
                        new JButtonStopServiceAction());
                optionsView.getJPanelOptionsService().getJButtonTest().addActionListener(
                        new JButtonTestServiceAction());
            } catch (OSNotSupportedException e) {
            }
            optionsView.getJPanelOptionsService().getJComboBoxOS().addItemListener(
                    new JComboBoxOSItemListener());
        }

        optionsView.getJPanelOptionsLogging().getJCheckBoxEnableLogging().addActionListener(
                new JCheckBoxEnableLoggingAction());
        optionsView.getRootPane().registerKeyboardAction(
                optionsView.getJButtonCancel().getActionListeners()[0],
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
        setEnableLogging();
        optionsView.setVisible(true);
    }

    private void setInterval() throws NumberFormatException, InvalidIntervalException {
        int hours, minutes, seconds;
        try {
            hours = Integer.valueOf(optionsView.getJPanelOptionsMonitor().getJTextFieldHours().getText());
            minutes = Integer.valueOf(optionsView.getJPanelOptionsMonitor().getJTextFieldMinutes().getText());
            seconds = Integer.valueOf(optionsView.getJPanelOptionsMonitor().getJTextFieldSeconds().getText());
            if ((hours < 0) || (minutes < 0 || minutes > 59) || (seconds < 0 || seconds > 59)) {
                throw new NumberFormatException();
            }
            ipMonitor.setInterval(hours * TimeUnitConverter.HOURS + minutes * TimeUnitConverter.MINUTES + seconds);
        } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null,
                    "Interval is not correct. Please enter a valid interval.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            optionsView.getJPanelOptionsMonitor().getJTextFieldHours().requestFocus();
            throw e1;
        } catch (InvalidIntervalException e2) {
            JOptionPane.showMessageDialog(null,
                    "Interval is not correct. It can not be less than 10 minutes.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            throw e2;
        }
    }

    private void setAutoStart() {
        ConfigurationManager.getInstance().setAutostart(
                optionsView.getJPanelOptionsMonitor().getJCheckBoxAutoStart().isSelected());
    }

    private void setUrl() throws MalformedURLException {
        try {
            ipMonitor.setUrl(optionsView.getJPanelOptionsMonitor().getJTextFieldURL().getText());
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null,
                    "The URL is not correct. Use a sintax similar to:\nhttp://www.server.com",
                    "Error", JOptionPane.ERROR_MESSAGE);
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
            LookAndFeelInfoWrapper lookAndFeelInfoWrapper = ((LookAndFeelInfoWrapper) optionsView.getJPanelOptionsInterface().getJListLookAndFeel().getSelectedValue());
            if (UIManager.getLookAndFeel().getName().equals(lookAndFeelInfoWrapper.getName())) {
                return;
            }
            try {
                ConfigurationManager.getInstance().getVisualConfigurationManager().setLookAndFeelClassName(lookAndFeelInfoWrapper.getClassName());
                UIManager.setLookAndFeel(ConfigurationManager.getInstance().getVisualConfigurationManager().getLookAndFeelClassName());
                SwingUtilities.updateComponentTreeUI(optionsView.getOwner());
                SwingUtilities.updateComponentTreeUI(optionsView);
                optionsView.pack();
                optionsView.getOwner().pack();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void setEnableServiceChange() {
        AbstractService selectedService = ServiceManager.getInstance().getServiceFromClass((Class) optionsView.getJPanelOptionsService().
                getJComboBoxOS().getSelectedItem().getClass());
        try {
            if (ServiceManager.getInstance().getService().equals(selectedService)) {
                serviceOK = true;
            } else {
                serviceOK = !ServiceManager.getInstance().getService().isInstalled();
            }
        } catch (Exception e) {
            serviceOK = false;
        }
        Component[] components = optionsView.getJPanelOptionsService().getJPanelInnerPanel().getComponents();
        for (Component component : components) {
            if (component.getClass().equals(JButton.class)) {
                component.setEnabled(serviceOK);
            }
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
        MainLogger.getInstance().setEnabled(
                optionsView.getJPanelOptionsLogging().getJCheckBoxEnableLogging().isSelected());
    }

    private void setMaxDaysToKeepLogs() throws NumberFormatException, InvalidMaxDaysToKeepLogs {
        int days;
        try {
            days = Integer.valueOf(optionsView.getJPanelOptionsLogging().getJTextFieldDaysToKeepLogs().getText());
            MainLogger.getInstance().setMaxDaysToKeepLogs(days);
        } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null,
                    "The number of days must be a positive integer. Please enter a valid number of days.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            optionsView.getJPanelOptionsLogging().getJTextFieldDaysToKeepLogs().requestFocus();
            throw e1;
        } catch (InvalidMaxDaysToKeepLogs e2) {
            if (MainLogger.getInstance().isEnabled()) {
                JOptionPane.showMessageDialog(null,
                        "The number of days must be a positive integer. Please enter a valid number of days.\n" +
                        "If you don't want to keep any log you should disable logging.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                optionsView.getJPanelOptionsLogging().getJTextFieldDaysToKeepLogs().requestFocus();
                throw e2;
            } else {
                MainLogger.getInstance().setMaxDaysToKeepLogs(IPMonitorProperties.OPTIONS_MONITOR_MAX_DAYS_TO_KEEP_LOGS_VALUE);
            }
        }
    }

    private void setOsId() throws Exception {
        if (!serviceOK) {
            JOptionPane.showMessageDialog(null,
                    "There is a " + serviceName + " currently installed that belongs to a different operative system.\n" +
                    "Uninstall this " + serviceName + " and then try installing the selected " + serviceName + " again.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            optionsView.getJPanelOptionsService().getJComboBoxOS().requestFocus();
            throw new Exception();
        }
        int osId = OSManager.getInstance().getIdForClass(optionsView.getJPanelOptionsService().getJComboBoxOS().getSelectedItem().getClass());
        try {
            ConfigurationManager.getInstance().setOsId(osId);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "The selected operative system is not valid. Please select different one.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            optionsView.getJPanelOptionsService().getJComboBoxOS().requestFocus();
            throw e;
        }
    }

    private void saveSettings() {
        MainLogger.getInstance().deleteOldFiles();
        new IPMonitorPropertiesManager(ipMonitor).saveToFile();
        try {
            if (ServiceManager.getInstance().getService().isRunning()) {
                int answer = JOptionPane.showConfirmDialog(null, "The IP Monitor service is currently running. In order to apply the current settings\n" +
                        "the service must be restarted. Do you want to restart the service now?", "Service restart confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    ServiceManager.getInstance().getService().stop();
                    ServiceManager.getInstance().getService().start();
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (OSNotSupportedException e2) {
            //I don't care if the OS is not supported
        }
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
                JOptionPane.showMessageDialog(null,
                        "An error has been detected while opening file\n" + AudioConfiguration.getInstance().getFileName(), "Error",
                        JOptionPane.ERROR_MESSAGE);
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
                    "This test might take a few minutes depending on network\n" +
                    "congestion and email notification configuration.\n\n" +
                    "Close this dialog to start the test.", "Please wait",
                    JOptionPane.INFORMATION_MESSAGE);
            try {
                MailPerformer.getInstance().sendMail(ipMonitor.getLastIP(),
                        "[NEW_IP_HERE]");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "An error has been detected while trying to send an email.\n\n" +
                        "Check the network settings and the email notification configuration.", "Error",
                        JOptionPane.ERROR_MESSAGE);
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
            VisualPerformer.getInstance().displayMessage(ipMonitor.getLastIP(),
                    "[NEW_IP_HERE]");
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
                setEnableServiceChange();
                CommandPerformer.getInstance().executeCommand(ipMonitor.getLastIP(),
                        "[NEW_IP_HERE]");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "An error has been detected while executing command\n" + CommandConfiguration.getInstance().getCommand(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showOSNotSupportedDialog() {
        JOptionPane.showMessageDialog(null,
                "This operative system is not supported", "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    private class JButtonInstallServiceAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            try {
                try {
                    ServiceManager.getInstance().getService().install();
                } catch (OSNotSupportedException e) {
                    showOSNotSupportedDialog();
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "There has been an error while installing the " +
                        serviceName + ".",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class JButtonUninstallServiceAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            try {
                ServiceManager.getInstance().getService().uninstall();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "There has been an error while uninstalling the " +
                        serviceName + ".",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } catch (OSNotSupportedException e) {
                showOSNotSupportedDialog();
            }
        }
    }

    private class JButtonStartServiceAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            try {
                ServiceManager.getInstance().getService().start();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "There has been an error while starting the " +
                        serviceName + ".",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } catch (OSNotSupportedException e) {
                showOSNotSupportedDialog();
            }
        }
    }

    private class JButtonStopServiceAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            try {
                ServiceManager.getInstance().getService().stop();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "There has been an error while stopping the " +
                        serviceName + ".",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } catch (OSNotSupportedException e) {
                showOSNotSupportedDialog();
            }
        }
    }

    private class JButtonTestServiceAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            try {
                JOptionPane.showMessageDialog(null, ServiceManager.getInstance().
                        getService().test().getExitCodeDescription(), "Test result",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "There has been an error while testing the state of the " +
                        serviceName + ".",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } catch (OSNotSupportedException e) {
                showOSNotSupportedDialog();
            }
        }
    }

    private class JCheckBoxEnableLoggingAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            setEnableLogging();
        }
    }

    private class JComboBoxOSItemListener implements ItemListener {

        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                setEnableServiceChange();
            }
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

    private class JButtonCancelAction implements ActionListener {

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
            try {
                setOsId();
            } catch (Exception ex) {
                isEveryhingOk = false;
            }
            saveSettings();
        }

        public boolean isEverythingOk() {
            return isEveryhingOk;
		}
	}
}
