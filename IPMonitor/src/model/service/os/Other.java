/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.service.os;

import model.service.services.*;

public class Other extends AbstractOS {

    public String getName() {
        return "Other";
    }

    protected String getFileName() {
        return "other";
    }

    public AbstractService getService() {
        return OtherService.getInstance(getFileName());
    }
}
