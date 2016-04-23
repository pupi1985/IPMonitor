/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.notification.configuration;

import java.awt.TrayIcon.*;

public class VisualConfiguration {

    private static VisualConfiguration instance;
    private String title;
    private String text;
    private MessageType icon;

    private VisualConfiguration() {
    }

    public static VisualConfiguration getInstance() {
        if (instance == null) {
            instance = new VisualConfiguration();
        }
        return instance;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MessageType getIcon() {
        return icon;
    }

    public void setIcon(MessageType icon) {
        this.icon = icon;
    }
}
