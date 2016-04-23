/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.service.os;

import model.service.services.AbstractService;

public abstract class AbstractOS {

    public abstract String getName();

    protected abstract String getFileName();

    public abstract AbstractService getService();

    public String toString() {
        return getName();
    }
}
