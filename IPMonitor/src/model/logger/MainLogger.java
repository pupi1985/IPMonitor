/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.logger;

import java.io.*;
import java.text.*;
import java.util.*;
import model.configuration.*;
import model.extras.*;
import model.logger.exceptions.*;

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
                    ConfigurationManager.getInstance().getLogFilesDirectory() +
                    new SimpleDateFormat(ConfigurationManager.getInstance().getLogFileNameFormat()).format(date) +
                    ".log");
            fileExists = logFile.exists();
            fileWriter = new FileWriter(logFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(CommonFunctions.getInstance().getSystemFormattedDateTime(date) + " > " + text);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            if (!fileExists) {
                deleteOldFiles();
            }
        }
    }

    public void deleteOldFiles() {
        File[] files = new File(ConfigurationManager.getInstance().getLogFilesDirectory()).listFiles(new LogFileFilter());
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
                    e.printStackTrace();
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
