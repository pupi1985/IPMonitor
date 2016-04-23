/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.notification.configuration;

public class AudioConfiguration extends AbstractConfiguration {

    private static AudioConfiguration instance;
    private String fileName;

    private AudioConfiguration() {
    }

    public static AudioConfiguration getInstance() {
        if (instance == null) {
            instance = new AudioConfiguration();
        }
        return instance;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
