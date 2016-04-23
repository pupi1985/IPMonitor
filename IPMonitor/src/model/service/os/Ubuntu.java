/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.service.os;

import model.service.services.*;

public class Ubuntu extends AbstractOS {

    public String getName() {
        return "Ubuntu";
    }

    protected String getFileName() {
        return "debian.sh";
    }

    public AbstractService getService() {
        return LinuxServiceX86.getInstance(getFileName());
    }
}
