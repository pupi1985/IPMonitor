/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.service.os;

import model.service.services.*;

public class Windows extends AbstractOS {

    public String getName() {
        return "Windows";
    }

    protected String getFileName() {
        return "wrapper-windows-x86-32.exe";
    }

    public AbstractService getService() {
        return WindowsService.getInstance(getFileName());
    }
}
