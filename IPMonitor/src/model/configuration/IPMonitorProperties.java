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

import javax.swing.UIManager;

public class IPMonitorProperties {

    public static final String MAINVIEW_LOCATION_X = "MainViewLocationX";
    public static final String MAINVIEW_LOCATION_Y = "MainViewLocationY";
    public static final String MAINVIEW_SIZE_X = "MainViewSizeX";
    public static final String MAINVIEW_SIZE_Y = "MainViewSizeY";
    public static final String OPTIONS_MONITOR_INTERVAL = "OptionsMonitorInterval";
    public static final int OPTIONS_MONITOR_INTERVAL_VALUE = 600;
    public static final String OPTIONS_MONITOR_AUTOSTART = "OptionsMonitorAutoStart";
    public static final boolean OPTIONS_MONITOR_AUTOSTART_VALUE = false;
    public static final String OPTIONS_MONITOR_URL = "OptionsMonitorURL";
    public static final String OPTIONS_MONITOR_URL_VALUE = "http://checkip.dyndns.org";
    public static final String OPTIONS_MONITOR_LOGGING_ENABLED = "OptionsMonitorLoggingEnabled";
    public static final boolean OPTIONS_MONITOR_LOGGING_ENABLED_VALUE = false;
    public static final String OPTIONS_MONITOR_MAX_DAYS_TO_KEEP_LOGS = "OptionsMonitorMaxDaysToKeepLogs";
    public static final int OPTIONS_MONITOR_MAX_DAYS_TO_KEEP_LOGS_VALUE = 3;
    public static final String OPTIONS_NOTIFICATION_AUDIO = "OptionsNotificationAudio";
    public static final boolean OPTIONS_NOTIFICATION_AUDIO_VALUE = false;
    public static final String OPTIONS_NOTIFICATION_MAIL = "OptionsNotificationMail";
    public static final boolean OPTIONS_NOTIFICATION_MAIL_VALUE = false;
    public static final String OPTIONS_NOTIFICATION_VISUAL = "OptionsNotificationVisual";
    public static final boolean OPTIONS_NOTIFICATION_VISUAL_VALUE = false;
    public static final String OPTIONS_NOTIFICATION_COMMAND = "OptionsNotificationCommand";
    public static final boolean OPTIONS_NOTIFICATION_COMMAND_VALUE = false;
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_AUDIO_PATH = "OptionsNotificationConfigurationAudioPath";
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_AUDIO_PATH_VALUE = "";
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_SERVER = "OptionsNotificationConfigurationMailServer";
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_SERVER_VALUE = "";
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_PORT = "OptionsNotificationConfigurationMailPort";
    public static final int OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_PORT_VALUE = 25;
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_USER = "OptionsNotificationConfigurationMailUser";
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_USER_VALUE = "";
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_PASSWORD = "OptionsNotificationConfigurationMailPassword";
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_PASSWORD_VALUE = "";
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_AUTHENTICATION_REQUIRED = "OptionsNotificationConfigurationMailAuthenticationRequired";
    public static final boolean OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_AUTHENTICATION_REQUIRED_VALUE = false;
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_USE_SSL = "OptionsNotificationConfigurationMailUseSSL";
    public static final boolean OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_USE_SSL_VALUE = false;
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_FROM_NAME = "OptionsNotificationConfigurationMailFromName";
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_FROM_NAME_VALUE = "";
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_FROM_ADDRESS = "OptionsNotificationConfigurationMailFromAddress";
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_FROM_ADDRESS_VALUE = "";
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_TO_ADDRESSES = "OptionsNotificationConfigurationMailToAddresses";
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_TO_ADDRESSES_VALUE = "";
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_SUBJECT = "OptionsNotificationConfigurationMailSubject";
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_SUBJECT_VALUE = "";
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_TEXT = "OptionsNotificationConfigurationMailText";
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_TEXT_VALUE = "";
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_USE_HTML = "OptionsNotificationConfigurationMailUseHTML";
    public static final boolean OPTIONS_NOTIFICATION_CONFIGURATION_MAIL_USE_HTML_VALUE = true;
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_VISUAL_TITLE = "OptionsNotificationConfigurationVisualTitle";
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_VISUAL_TITLE_VALUE = "IP change detected";
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_VISUAL_TEXT = "OptionsNotificationConfigurationVisualText";
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_VISUAL_TEXT_VALUE = "From IP: %OLDIP%\nTo IP: %NEWIP%";
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_VISUAL_ICON = "OptionsNotificationConfigurationVisualIcon";
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_VISUAL_ICON_VALUE = "INFO";
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_COMMAND_PATH = "OptionsNotificationConfigurationCommand";
    public static final String OPTIONS_NOTIFICATION_CONFIGURATION_COMMAND_PATH_VALUE = "";
    public static final String OPTIONS_INTERFACE_LOOK_AND_FEEL_CLASS_NAME = "OptionsInterfaceLookAndFeelClassName";
    public static final String OPTIONS_INTERFACE_LOOK_AND_FEEL_CLASS_NAME_VALUE = UIManager.getCrossPlatformLookAndFeelClassName();
}
