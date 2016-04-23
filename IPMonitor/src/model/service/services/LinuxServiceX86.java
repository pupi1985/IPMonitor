/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.service.services;

import java.io.*;
import model.configuration.*;

public class LinuxServiceX86 extends AbstractService {

    private static LinuxServiceX86 instance;

    private LinuxServiceX86(String fileName) {
        this.fileName = fileName;
    }

    public static LinuxServiceX86 getInstance(String fileName) {
        if (instance == null) {
            instance = new LinuxServiceX86(fileName);
        }
        return instance;
    }

    public String getServiceName() {
        return "Daemon";
    }

    protected String getPreActionCommandLine() {
        return "./" + ConfigurationManager.getInstance().getWrapperNativeDirectory() + getFileName() + " ";
    }

    protected String getPostActionCommandLine() {
        return "";
    }

    protected void innerInstall() throws IOException {
        execWrapperAction("install");
    }

    protected void innerUninstall() throws IOException {
        execWrapperAction("uninstall");
    }

    protected void innerStart() throws IOException {
        execWrapperAction("start");
    }

    protected void innerStop() throws IOException {
        execWrapperAction("stop");
    }

    protected int innerTest() throws IOException {
        return execWrapperAction("test").exitValue();
    }
}
