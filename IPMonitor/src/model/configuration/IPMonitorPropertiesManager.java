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
import java.awt.TrayIcon.MessageType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.util.Properties;

import javax.mail.internet.AddressException;

import model.encryption.DESAlgorithm;
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
import model.notification.configuration.MailConfiguration;
import model.notification.configuration.VisualConfiguration;


public class IPMonitorPropertiesManager {

    private IPMonitor ipMonitor;

    public IPMonitorPropertiesManager(IPMonitor ipMonitor) {
        this.ipMonitor = ipMonitor;
    }

    public void loadFromFile() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(ConfigurationManager.getInstance().getConfigurationFilePath())));
        } catch (Exception e) {
            //This is executed when the file does not exist
        }
        loadMainViewLocation(properties);
        loadMainViewSize(properties);
        loadLookAndFeelClassName(properties);
        loadVisualNotification(properties);
        loadInterval(properties);
        loadAutostart(properties);
        loadURL(properties);
        loadLoggingEnabled(properties);
        loadMaxDaysToKeepLogs(properties);
        loadAudioNotification(properties);
        loadMailNotification(properties);
        loadCommandNotification(properties);
        loadAudioNotificationConfigurationPath(properties);
        loadMailNotificationConfigurationServer(properties);
        loadMallNotificationConfigurationPort(properties);
        loadMailNotificationConfigurationUser(properties);
        loadMailNotificationConfigurationPassword(properties);
        loadMailNotificationConfigurationAuthenticationRequired(properties);
        loadMailNotificationConfigurationUseSSL(properties);
        loadMailNotificationConfigurationFromName(properties);
        loadMailNotificationConfigurationFromAddress(properties);
        loadMailNotificationConfigurationToAddresses(properties);
        loadMailNotificationConfigurationSubject(properties);
        loadMailNotificationConfigurationText(properties);
        loadMailNotificationConfigurationUseHTML(properties);
        loadVisualNotificationConfigurationTitle(properties);
        loadVisualNotificationConfigurationText(properties);
        loadVisualNotificationConfigurationIcon(properties);
        loadCommandNotificationConfigurationPath(properties);
    }

    public void saveToFile() {
        Properties properties = new Properties();
        saveMainViewLocation(properties);
        saveMainViewSize(properties);
        saveLookAndFeelClassName(properties);
        saveVisualNotification(properties);
        saveInterval(properties);
        saveAutostart(properties);
        saveURL(properties);
        saveLoggingEnabled(properties);
        saveMaxDaysToKeepLogs(properties);
        saveAudioNotification(properties);
        saveMailNotification(properties);
        saveCommandNotification(properties);
        saveAudioNotificationConfigurationPath(properties);
        saveMailNotificationConfigurationServer(properties);
        saveMallNotificationConfigurationPort(properties);
        saveMailNotificationConfigurationUser(properties);
        saveMailNotificationConfigurationPassword(properties);
        saveMailNotificationConfigurationAuthenticationRequired(properties);
        saveMailNotificationConfigurationUseSSL(properties);
        saveMailNotificationConfigurationFromName(properties);
        saveMailNotificationConfigurationFromAddress(properties);
        saveMailNotificationConfigurationToAddresses(properties);
        saveMailNotificationConfigurationSubject(properties);
        saveMailNotificationConfigurationText(properties);
        saveMailNotificationConfigurationUseHTML(properties);
        saveVisualNotificationConfigurationTitle(properties);
        saveVisualNotificationConfigurationText(properties);
        saveVisualNotificationConfigurationIcon(properties);
        saveCommandNotificationConfigurationPath(properties);
        try {
            properties.store(new FileOutputStream(new File(ConfigurationManager.getInstance().getConfigurationFilePath())), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMainViewLocation(Properties properties) {
        try {
            int xPosition = Integer.valueOf(properties.getProperty(IPMonitorProperties.MAINVIEW_LOCATION_X));
            int yPosition = Integer.valueOf(properties.getProperty(IPMonitorProperties.MAINVIEW_LOCATION_Y));
            try {
                ConfigurationManager.getInstance().getVisualConfigurationManager().setMainViewLocation(new Point(xPosition, yPosition));
            } catch (Exception e2) {
                //This is executed when running as a service
            }
        } catch (Exception e1) {
            //This is executed when the file does not exist
        }
    }

    private void saveMainViewLocation(Properties properties) {
        try {
            properties.setProperty(IPMonitorProperties.MAINVIEW_LOCATION_X, String.valueOf(ConfigurationManager.getInstance().getVisualConfigurationManager().getMainViewLocation().x));
            properties.setProperty(IPMonitorProperties.MAINVIEW_LOCATION_Y, String.valueOf(ConfigurationManager.getInstance().getVisualConfigurationManager().getMainViewLocation().y));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadMainViewSize(Properties properties) {
        try {
            int xPosition = Integer.valueOf(properties.getProperty(IPMonitorProperties.MAINVIEW_SIZE_X));
            int yPosition = Integer.valueOf(properties.getProperty(IPMonitorProperties.MAINVIEW_SIZE_Y));
            try {
                ConfigurationManager.getInstance().getVisualConfigurationManager().setMainViewSize(new Dimension(xPosition, yPosition));
            } catch (Exception e) {
                //This is executed when running as a service
            }
        } catch (Exception e) {
            //This is executed when the file does not exist
        }
    }

    private void saveMainViewSize(Properties properties) {
        try {
            properties.setProperty(IPMonitorProperties.MAINVIEW_SIZE_X, String.valueOf(ConfigurationManager.getInstance().getVisualConfigurationManager().getMainViewSize().width));
            properties.setProperty(IPMonitorProperties.MAINVIEW_SIZE_Y, String.valueOf(ConfigurationManager.getInstance().getVisualConfigurationManager().getMainViewSize().height));
        } catch (Exception e) {
            //This is executed when running as a service
        }
    }

    private void loadInterval(Properties properties) {
        int interval;
        try {
            interval = Integer.valueOf(properties.getProperty(
                    IPMonitorProperties.OPTIONS_MONITOR_INTERVAL,
                    String.valueOf(IPMonitorProperties.OPTIONS_MONITOR_INTERVAL_VALUE)));
        } catch (NumberFormatException e) {
            interval = IPMonitorProperties.OPTIONS_MONITOR_INTERVAL_VALUE;
        }
        try {
            ipMonitor.setInterval(interval);
        } catch (InvalidIntervalException e) {
            try {
                ipMonitor.setInterval(IPMonitorProperties.OPTIONS_MONITOR_INTERVAL_VALUE);
            } catch (InvalidIntervalException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void saveInterval(Properties properties) {
        properties.setProperty(IPMonitorProperties.OPTIONS_MONITOR_INTERVAL,
                String.valueOf(ipMonitor.getInterval()));
    }

    private void loadAutostart(Properties properties) {
        ConfigurationManager.getInstance().setAutostart(
                Boolean.valueOf(properties.getProperty(
                IPMonitorProperties.OPTIONS_MONITOR_AUTOSTART,
                String.valueOf(IPMonitorProperties.OPTIONS_MONITOR_AUTOSTART_VALUE))));
    }

    private void saveAutostart(Properties properties) {
        properties.setProperty(IPMonitorProperties.OPTIONS_MONITOR_AUTOSTART,
                String.valueOf(ConfigurationManager.getInstance().isAutostart()));
    }

    private void loadURL(Properties properties) {
        try {
            ipMonitor.setUrl(properties.getProperty(
                    IPMonitorProperties.OPTIONS_MONITOR_URL,
                    IPMonitorProperties.OPTIONS_MONITOR_URL_VALUE));
        } catch (MalformedURLException e1) {
            try {
                ipMonitor.setUrl(IPMonitorProperties.OPTIONS_MONITOR_URL_VALUE);
            } catch (MalformedURLException e2) {
                e2.printStackTrace();
            }
        }
    }

    private void saveURL(Properties properties) {
        properties.setProperty(IPMonitorProperties.OPTIONS_MONITOR_URL,
                ipMonitor.getUrl());
    }

    private void loadLoggingEnabled(Properties properties) {
        MainLogger.getInstance().setEnabled(
                Boolean.valueOf(properties.getProperty(
                IPMonitorProperties.OPTIONS_MONITOR_LOGGING_ENABLED,
                String.valueOf(IPMonitorProperties.OPTIONS_MONITOR_LOGGING_ENABLED_VALUE))));
    }

    private void saveLoggingEnabled(Properties properties) {
        properties.setProperty(IPMonitorProperties.OPTIONS_MONITOR_LOGGING_ENABLED,
                String.valueOf(MainLogger.getInstance().isEnabled()));
    }

    private void loadMaxDaysToKeepLogs(Properties properties) {
        int maxDays;
        try {
            maxDays = Integer.parseInt(properties.getProperty(
                    IPMonitorProperties.OPTIONS_MONITOR_MAX_DAYS_TO_KEEP_LOGS,
                    String.valueOf(IPMonitorProperties.OPTIONS_MONITOR_MAX_DAYS_TO_KEEP_LOGS_VALUE)));
        } catch (NumberFormatException e) {
            maxDays = IPMonitorProperties.OPTIONS_MONITOR_MAX_DAYS_TO_KEEP_LOGS_VALUE;
        }

        try {
            MainLogger.getInstance().setMaxDaysToKeepLogs(maxDays);
        } catch (InvalidMaxDaysToKeepLogs e) {
            try {
                MainLogger.getInstance().setMaxDaysToKeepLogs(IPMonitorProperties.OPTIONS_MONITOR_MAX_DAYS_TO_KEEP_LOGS_VALUE);
            } catch (InvalidMaxDaysToKeepLogs e1) {
                e1.printStackTrace();
            }
        }
    }

    private void saveMaxDaysToKeepLogs(Properties properties) {
        properties.setProperty(
                IPMonitorProperties.OPTIONS_MONITOR_MAX_DAYS_TO_KEEP_LOGS,
                String.valueOf(MainLogger.getInstance().getMaxDaysToKeepLogs()));
    }

    private void loadAudioNotification(Properties properties) {
        if (Boolean.valueOf(properties.getProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_AUDIO,
                String.valueOf(IPMonitorProperties.OPTIONS_NOTIFICATION_AUDIO_VALUE)))) {
            ipMonitor.addIPMonitorListener(AudioNotification.getInstance());
        }

    }

    private void saveAudioNotification(Properties properties) {
        properties.setProperty(IPMonitorProperties.OPTIONS_NOTIFICATION_AUDIO,
                String.valueOf(ipMonitor.hasNotification(AudioNotification.getInstance())));
    }

    private void loadMailNotification(Properties properties) {
        if (Boolean.valueOf(properties.getProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_MAIL,
                String.valueOf(IPMonitorProperties.OPTIONS_NOTIFICATION_MAIL_VALUE)))) {
            ipMonitor.addIPMonitorListener(MailNotification.getInstance());
        }

    }

    private void saveMailNotification(Properties properties) {
        properties.setProperty(IPMonitorProperties.OPTIONS_NOTIFICATION_MAIL,
                String.valueOf(ipMonitor.hasNotification(MailNotification.getInstance())));
    }

    private void loadVisualNotification(Properties properties) {
        try {
            if (!ConfigurationManager.getInstance().getVisualConfigurationManager().isSystemTraySupported()) {
                return;
            }
        } catch (Exception e) {
            //This is executed when running as a service
        }

        if (Boolean.valueOf(properties.getProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_VISUAL,
                String.valueOf(IPMonitorProperties.OPTIONS_NOTIFICATION_VISUAL_VALUE)))) {
            ipMonitor.addIPMonitorListener(VisualNotification.getInstance());
        }

    }

    private void saveVisualNotification(Properties properties) {
        try {
            if (!ConfigurationManager.getInstance().getVisualConfigurationManager().isSystemTraySupported()) {
                return;
            }
        } catch (Exception e) {
            //This is executed when running as a service
        }

        properties.setProperty(IPMonitorProperties.OPTIONS_NOTIFICATION_VISUAL,
                String.valueOf(ipMonitor.hasNotification(VisualNotification.getInstance())));
    }

    private void loadLookAndFeelClassName(Properties properties) {
        try {
            ConfigurationManager.getInstance().getVisualConfigurationManager().setLookAndFeelClassName(
                    properties.getProperty(
                    IPMonitorProperties.OPTIONS_INTERFACE_LOOK_AND_FEEL_CLASS_NAME,
                    IPMonitorProperties.OPTIONS_INTERFACE_LOOK_AND_FEEL_CLASS_NAME_VALUE));
        } catch (Exception e) {
            //This is executed when running as a service
        }
    }

    private void saveLookAndFeelClassName(Properties properties) {
        try {
            properties.setProperty(
                    IPMonitorProperties.OPTIONS_INTERFACE_LOOK_AND_FEEL_CLASS_NAME,
                    ConfigurationManager.getInstance().getVisualConfigurationManager().getLookAndFeelClassName());
        } catch (Exception e) {
            //This is executed when running as a service
        }
    }

    private void loadCommandNotification(Properties properties) {
        //if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
        if (Boolean.valueOf(properties.getProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_COMMAND,
                String.valueOf(IPMonitorProperties.OPTIONS_NOTIFICATION_COMMAND_VALUE)))) {
            ipMonitor.addIPMonitorListener(CommandNotification.getInstance());
        }
        // }
    }

    private void saveCommandNotification(Properties properties) {
        // if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
        properties.setProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_COMMAND, String.valueOf(ipMonitor.hasNotification(CommandNotification.getInstance())));
        // }
    }

    private void loadAudioNotificationConfigurationPath(Properties properties) {
        AudioConfiguration.getInstance().setFileName(
                properties.getProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_AUDIO_PATH,
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_AUDIO_PATH_VALUE));
    }

    private void saveAudioNotificationConfigurationPath(Properties properties) {
        properties.setProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_AUDIO_PATH,
                AudioConfiguration.getInstance().getFileName());
    }

    private void loadMailNotificationConfigurationServer(Properties properties) {
        MailConfiguration.getInstance().setHost(
                properties.getProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_SERVER,
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_SERVER_VALUE));
    }

    private void saveMailNotificationConfigurationServer(Properties properties) {
        properties.setProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_SERVER,
                MailConfiguration.getInstance().getHost());
    }

    private void loadMallNotificationConfigurationPort(Properties properties) {
        int port;
        try {
            port = Integer.valueOf(properties.getProperty(
                    IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_PORT,
                    String.valueOf(IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_PORT_VALUE)));
        } catch (NumberFormatException e) {
            port = IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_PORT_VALUE;
        }

        try {
            MailConfiguration.getInstance().setPort(port);
        } catch (NumberFormatException e1) {
            try {
                MailConfiguration.getInstance().setPort(
                        IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_PORT_VALUE);
            } catch (NumberFormatException e2) {
                e2.printStackTrace();
            }
        }
    }

    private void saveMallNotificationConfigurationPort(Properties properties) {
        properties.setProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_PORT,
                String.valueOf(MailConfiguration.getInstance().getPort()));
    }

    private void loadMailNotificationConfigurationUser(Properties properties) {
        MailConfiguration.getInstance().setUser(
                properties.getProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_USER,
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_USER_VALUE));
    }

    private void saveMailNotificationConfigurationUser(Properties properties) {
        properties.setProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_USER,
                MailConfiguration.getInstance().getUser());
    }

    private void loadMailNotificationConfigurationPassword(Properties properties) {
        MailConfiguration.getInstance().setPassword(
                DESAlgorithm.getInstance().decrypt(
                properties.getProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_PASSWORD,
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_PASSWORD_VALUE)));
    }

    private void saveMailNotificationConfigurationPassword(Properties properties) {
        String password = DESAlgorithm.getInstance().encrypt(
                MailConfiguration.getInstance().getPassword());
        properties.setProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_PASSWORD,
                (password == null) ? IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_PASSWORD_VALUE
                : password);
    }

    private void loadMailNotificationConfigurationAuthenticationRequired(
            Properties properties) {
        MailConfiguration.getInstance().setAuthenticationRequired(
                Boolean.valueOf(properties.getProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_AUTHENTICATION_REQUIRED,
                String.valueOf(IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_AUTHENTICATION_REQUIRED_VALUE))));
    }

    private void saveMailNotificationConfigurationAuthenticationRequired(
            Properties properties) {
        properties.setProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_AUTHENTICATION_REQUIRED,
                String.valueOf(MailConfiguration.getInstance().isAuthenticationRequired()));
    }

    private void loadMailNotificationConfigurationUseSSL(Properties properties) {
        MailConfiguration.getInstance().setSSL(
                Boolean.valueOf(properties.getProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_USE_SSL,
                String.valueOf(IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_USE_SSL_VALUE))));
    }

    private void saveMailNotificationConfigurationUseSSL(Properties properties) {
        properties.setProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_USE_SSL,
                String.valueOf(MailConfiguration.getInstance().isSSL()));
    }

    private void loadMailNotificationConfigurationFromName(Properties properties) {
        MailConfiguration.getInstance().setFromName(
                properties.getProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_FROM_NAME,
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_FROM_NAME_VALUE));
    }

    private void saveMailNotificationConfigurationFromName(Properties properties) {
        properties.setProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_FROM_NAME,
                MailConfiguration.getInstance().getFromName());
    }

    private void loadMailNotificationConfigurationFromAddress(
            Properties properties) {
        try {
            MailConfiguration.getInstance().setFromAddress(
                    properties.getProperty(
                    IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_FROM_ADDRESS,
                    IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_FROM_ADDRESS_VALUE));
        } catch (AddressException e) {
            try {
                MailConfiguration.getInstance().setFromAddress(
                        IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_FROM_ADDRESS_VALUE);
            } catch (AddressException e1) {
                e1.printStackTrace();
            }
        }

    }

    private void saveMailNotificationConfigurationFromAddress(
            Properties properties) {
        properties.setProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_FROM_ADDRESS,
                MailConfiguration.getInstance().getFromAddress());
    }

    private void loadMailNotificationConfigurationToAddresses(
            Properties properties) {
        try {

            MailConfiguration.getInstance().setToAddresses(
                    properties.getProperty(
                    IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_TO_ADDRESSES,
                    IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_TO_ADDRESSES_VALUE));
        } catch (AddressException e) {
            try {
                MailConfiguration.getInstance().setToAddresses(
                        IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_TO_ADDRESSES_VALUE);
            } catch (AddressException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void saveMailNotificationConfigurationToAddresses(
            Properties properties) {
        properties.setProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_TO_ADDRESSES,
                MailConfiguration.getInstance().getToAddresses());
    }

    private void loadMailNotificationConfigurationSubject(Properties properties) {
        MailConfiguration.getInstance().setSubject(
                properties.getProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_SUBJECT,
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_SUBJECT_VALUE));
    }

    private void saveMailNotificationConfigurationSubject(Properties properties) {
        properties.setProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_SUBJECT,
                MailConfiguration.getInstance().getSubject());
    }

    private void loadMailNotificationConfigurationText(Properties properties) {
        MailConfiguration.getInstance().setText(
                properties.getProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_TEXT,
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_TEXT_VALUE));
    }

    private void saveMailNotificationConfigurationText(Properties properties) {
        properties.setProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_TEXT,
                MailConfiguration.getInstance().getText());
    }

    private void loadMailNotificationConfigurationUseHTML(Properties properties) {
        MailConfiguration.getInstance().setHTML(
                Boolean.valueOf(properties.getProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_USE_HTML,
                String.valueOf(IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_USE_HTML_VALUE))));
    }

    private void saveMailNotificationConfigurationUseHTML(Properties properties) {
        properties.setProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_USE_HTML,
                String.valueOf(MailConfiguration.getInstance().isHTML()));
    }

    private void loadVisualNotificationConfigurationTitle(Properties properties) {
        VisualConfiguration.getInstance().setTitle(
                properties.getProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_VISUAL_TITLE,
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_VISUAL_TITLE_VALUE));
    }

    private void saveVisualNotificationConfigurationTitle(Properties properties) {
        properties.setProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_VISUAL_TITLE,
                VisualConfiguration.getInstance().getTitle());
    }

    private void loadVisualNotificationConfigurationText(Properties properties) {
        VisualConfiguration.getInstance().setText(
                properties.getProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_VISUAL_TEXT,
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_VISUAL_TEXT_VALUE));
    }

    private void saveVisualNotificationConfigurationText(Properties properties) {
        properties.setProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_VISUAL_TEXT,
                VisualConfiguration.getInstance().getText());
    }

    private void loadVisualNotificationConfigurationIcon(Properties properties) {
        String iconText = properties.getProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_VISUAL_ICON,
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_VISUAL_ICON_VALUE);
        MessageType icon;
        try {
            icon = MessageType.valueOf(iconText);
        } catch (IllegalArgumentException e) {
            icon = MessageType.valueOf(IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_VISUAL_ICON_VALUE);
        }
        VisualConfiguration.getInstance().setIcon(icon);
    }

    private void saveVisualNotificationConfigurationIcon(Properties properties) {
        properties.setProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_VISUAL_ICON,
                VisualConfiguration.getInstance().getIcon().name());
    }

    private void loadCommandNotificationConfigurationPath(Properties properties) {
        CommandConfiguration.getInstance().setCommand(
                properties.getProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_COMMAND_PATH,
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_COMMAND_PATH_VALUE));
    }

    private void saveCommandNotificationConfigurationPath(Properties properties) {
        properties.setProperty(
                IPMonitorProperties.OPTIONS_NOTIFICATION_CONFIGURATION_COMMAND_PATH,
                CommandConfiguration.getInstance().getCommand());
    }

}
