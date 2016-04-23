/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.service.services;

import java.io.*;
import model.configuration.*;

public class WindowsService extends AbstractService {

    private static WindowsService instance;

    private WindowsService(String fileName) {
        this.fileName = fileName;
    }

    public static WindowsService getInstance(String fileName) {
        if (instance == null) {
            instance = new WindowsService(fileName);
        }
        return instance;
    }

    public String getServiceName() {
        return "Service";
    }

    protected String getPreActionCommandLine() {
        return ConfigurationManager.getInstance().getWrapperNativeDirectory() + getFileName() + " ";
    }

    protected String getPostActionCommandLine() {
        return " ../common/wrapper.conf";
    }

    protected void innerInstall() throws IOException {
        execWrapperAction("--install");
    }

    protected void innerUninstall() throws IOException {
        execWrapperAction("--remove");
    }

    protected void innerStart() throws IOException {
        execWrapperAction("--start");
    }

    protected void innerStop() throws IOException {
        execWrapperAction("--stop");
    }

    protected int innerTest() throws IOException {
        return execWrapperAction("--query").exitValue();
    }
}
