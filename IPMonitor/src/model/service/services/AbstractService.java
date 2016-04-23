/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.service.services;

import model.service.*;
import java.io.*;

public abstract class AbstractService {

    protected String fileName;

    public AbstractService() {
    }

    public abstract String getServiceName();

    protected String getFileName() {
        return fileName;
    }

    protected abstract String getPreActionCommandLine();

    protected abstract String getPostActionCommandLine();

    private Process exec(String fullCommandLine) throws IOException {
        Process proc = Runtime.getRuntime().exec(fullCommandLine);
        try {
            proc.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return proc;
    }

    protected Process execWrapperAction(String action) throws IOException {
        return exec(getPreActionCommandLine() + action + getPostActionCommandLine());
    }

    protected abstract void innerInstall() throws IOException;

    public void install() throws IOException {
        if (!isRunning()) {
            innerInstall();
        }
    }

    protected abstract void innerUninstall() throws IOException;

    public void uninstall() throws IOException {
        if (isRunning()) {
            stop();
        }
        innerUninstall();
    }

    protected abstract void innerStart() throws IOException;

    public void start() throws IOException {
        if (!isRunning()) {
            innerStart();
        }
    }

    protected abstract void innerStop() throws IOException;

    public void stop() throws IOException {
        innerStop();
    }

    protected abstract int innerTest() throws IOException;

    /* Windows wrapper returns EXITCODE=0 when the service is not installed so
     * if y perform a logical AND with 0 the return will be 0, so I also check
     * for the exit code to be equal to 0
     */
    private boolean hasThisExitCode(int realExitCode, ServiceExitCode testExitCode) {
        int testExitCodeNumber = testExitCode.getExitCode();
        if (realExitCode != testExitCodeNumber) {
            return ((realExitCode & testExitCodeNumber) > 0);
        } else {
            return true;
        }
    }

    public ServiceExitCode test() throws IOException {
        int exitCode = innerTest();
        ServiceExitCode[] orderedExitCodes = ServiceExitCode.values();
        for (ServiceExitCode testExitCode : orderedExitCodes) {
            if (hasThisExitCode(exitCode, testExitCode)) {
                return testExitCode;
            }
        }
        throw new IOException();
    }

    public boolean isRunning() throws IOException {
        return (test().equals(ServiceExitCode.RUNNING));
    }

    public boolean isInstalled() throws IOException {
        return (!test().equals(ServiceExitCode.NOT_INSTALLED));
    }
}