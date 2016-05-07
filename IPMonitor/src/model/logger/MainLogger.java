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

package model.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import model.configuration.ConfigurationManager;
import model.extras.CommonFunctions;
import model.logger.exceptions.InvalidMaxDaysToKeepLogs;

public class MainLogger {

    private static MainLogger instance;
    private int maxDaysToKeepLogs;
    private boolean enabled;

    public static MainLogger getInstance() {
        if (instance == null) {
            instance = new MainLogger();
        }
        return instance;
    }

    public int getMaxDaysToKeepLogs() {
        return maxDaysToKeepLogs;
    }

    public void setMaxDaysToKeepLogs(int maxDaysToKeepLogs) throws InvalidMaxDaysToKeepLogs {
        if (maxDaysToKeepLogs < 1) {
            throw new InvalidMaxDaysToKeepLogs();
        }
        this.maxDaysToKeepLogs = maxDaysToKeepLogs;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void write(String text) {
        if (!this.enabled)
            return;
        FileWriter fileWriter = null;
        boolean fileExists = true;
        try {
            Date date = new Date();
            File logFile = new File(
                    ConfigurationManager.getInstance().getLogFilesDirectoryPath() +
                    new SimpleDateFormat(ConfigurationManager.getInstance().getLogFileNameFormat()).format(date) +
                    ".log");
            fileExists = logFile.exists();
            fileWriter = new FileWriter(logFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(CommonFunctions.getInstance().getSystemFormattedDateTime(date) + " > " + text);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e1) {
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e2) {
            }
            if (!fileExists) {
                deleteOldFiles();
            }
        }
    }

    public void deleteOldFiles() {
        File[] files = new File(ConfigurationManager.getInstance().getLogFilesDirectoryPath()).listFiles(new LogFileFilter());
        Arrays.sort(files, new FileNameComparator());
        int filesExceeded = files.length - maxDaysToKeepLogs;
        for (; filesExceeded > 0; filesExceeded--) {
            files[filesExceeded - 1].delete();
        }
    }

    private class LogFileFilter implements FilenameFilter {

        public boolean accept(File file, String fileName) {
            if (fileName.endsWith(".log") && (fileName.length() == 14)) {
                try {
                    new SimpleDateFormat(ConfigurationManager.getInstance().getLogFileNameFormat()).parse(fileName.substring(0, 10));
                    return true;
                } catch (ParseException e) {
                }
            }
            return false;
        }
    }

    private class FileNameComparator implements Comparator<File> {

        public int compare(File file1, File file2) {
            return file1.getName().compareTo(file2.getName());
        }
    }
}
