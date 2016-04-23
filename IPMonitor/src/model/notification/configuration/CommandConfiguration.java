/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.notification.configuration;

public class CommandConfiguration extends AbstractConfiguration {

    private static CommandConfiguration instance;
    private String command;

    private CommandConfiguration() {
    }

    public static CommandConfiguration getInstance() {
        if (instance == null) {
            instance = new CommandConfiguration();
        }
        return instance;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
