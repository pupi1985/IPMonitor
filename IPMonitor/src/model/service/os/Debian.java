/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.service.os;

import model.service.services.*;

public class Debian extends AbstractOS {

    public String getName() {
        return "Debian";
    }

    protected String getFileName() {
        return "debian.sh";
    }

    public AbstractService getService() {
        return LinuxServiceX86.getInstance(getFileName());
    }
}
