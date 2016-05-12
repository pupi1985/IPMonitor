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

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import model.configuration.ConfigurationManager;

public class Main {

    private static final String OPTION_SERVICE = "service";

    private static final String OPTION_CONFIGURATION_FILE_PATH = "config-file-path";
    private static final String OPTION_LOG_DIRECTORY_PATH = "log-directory-path";
    private static final String OPTION_LAST_CHECK_FILE_PATH = "last-check-file-path";
    private static final String OPTION_WRAPPER_EXECUTABLE_DIRECTORY_PATH = "wrapper-executable-directory-path";
    private static final String OPTION_WRAPPER_SCRIPT_DIRECTORY_PATH = "wrapper-script-directory-path";

    public static void main(String[] args) {
        Options options = new Options();
        options.addOption(Option.builder().longOpt(OPTION_SERVICE).build());

        options.addOption(Option.builder().longOpt(OPTION_CONFIGURATION_FILE_PATH).hasArg().build());
        options.addOption(Option.builder().longOpt(OPTION_LOG_DIRECTORY_PATH).hasArg().build());
        options.addOption(Option.builder().longOpt(OPTION_LAST_CHECK_FILE_PATH).hasArg().build());
        options.addOption(Option.builder().longOpt(OPTION_WRAPPER_EXECUTABLE_DIRECTORY_PATH).hasArg().build());
        options.addOption(Option.builder().longOpt(OPTION_WRAPPER_SCRIPT_DIRECTORY_PATH).hasArg().build());

        try {
            CommandLine line = new DefaultParser().parse(options, args);

            ConfigurationManager.getInstance()
                .setConfigurationFilePath(line.getOptionValue(OPTION_CONFIGURATION_FILE_PATH));
            ConfigurationManager.getInstance()
                .setLogFilesDirectoryPath(line.getOptionValue(OPTION_LOG_DIRECTORY_PATH));
            ConfigurationManager.getInstance()
                .setLastCheckFilePath(line.getOptionValue(OPTION_LAST_CHECK_FILE_PATH));
            ConfigurationManager.getInstance()
                .setWrapperExecutableDirectoryPath(line.getOptionValue(OPTION_WRAPPER_EXECUTABLE_DIRECTORY_PATH));
            ConfigurationManager.getInstance()
                .setWrapperScriptDirectoryPath(line.getOptionValue(OPTION_WRAPPER_SCRIPT_DIRECTORY_PATH));

            if (line.hasOption(OPTION_SERVICE)) {
                new MainService(args);
            } else {
                new MainApplication();
            }
        } catch (ParseException exp) {
            new HelpFormatter().printHelp("IP Monitor", options);
        }
    }
}
