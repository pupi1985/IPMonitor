/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.service;

public enum ServiceExitCode {
    
    /* IMPORTANT: The order of these values is essential to get the state of the
     * service. First it should be checked for the service to be running, then
     * stopped and finally not installed. This is defined by the following order
     */

    RUNNING(2, "IP Monitor is installed and running."),
    STOPPED(1, "IP Monitor is installed but not running."),
    NOT_INSTALLED(0, "IP Monitor is not installed.");
    
    
    private int exitCode;
    private String exitCodeDescription;

    private ServiceExitCode(int exitCode, String exitCodeDescription) {
        this.exitCode = exitCode;
        this.exitCodeDescription = exitCodeDescription;
    }

    public int getExitCode() {
        return exitCode;
    }

    public String getExitCodeDescription() {
        return exitCodeDescription;
    }
}
