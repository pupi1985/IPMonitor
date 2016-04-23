/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.service.services;

import java.util.*;
import model.configuration.*;
import model.service.exceptions.*;
import model.service.os.*;

public class ServiceManager {

    private static ServiceManager instance;

    private ServiceManager() {
    }

    public static ServiceManager getInstance() {
        if (instance == null) {
            instance = new ServiceManager();
        }
        return instance;
    }

    public AbstractService getServiceFromClass(Class OSClass) {
        try {
            return ((AbstractOS) OSClass.newInstance()).getService();
        } catch (Exception e) {
            return null;
        }
    }

    public int getValidOSId() throws OSNotSupportedException {
        try {
            return OSManager.getInstance().getIdForClass(getValidOSClassesForOSFamily().get(0));
        } catch (Exception e) {
            throw new OSNotSupportedException();
        }
    }

    public AbstractService getService() throws OSNotSupportedException {
        try {
            return getServiceFromClass(OSManager.getInstance().getClassForID(
                    ConfigurationManager.getInstance().getOsId()));
        } catch (Exception e) {
            throw new OSNotSupportedException();
        }
    }

    public ArrayList<Class> getValidOSClassesForOSFamily() {
        ArrayList<Class> OSList = new ArrayList<Class>();
        if (OSManager.getInstance().isOSFamilyWindows()) {
            OSList.add(Windows.class);
        } else if (OSManager.getInstance().isOSFamilyLinux()) {
            if (OSManager.getInstance().isArchX86()) {
                OSList.add(Ubuntu.class);
                OSList.add(Debian.class);
            }
            OSList.add(Other.class);
        } else {
            // OSList.add(Other.class);
        }
        return OSList;
    }

    public boolean isValidIdForOsFamily(int id) {
        return getValidOSClassesForOSFamily().contains(OSManager.getInstance().getClassForID(id));
    }

    public boolean isValidClassForOSFamily(Class aClass) {
        return getValidOSClassesForOSFamily().contains(aClass);
    }

    public boolean isOSSupported() {
        return !getValidOSClassesForOSFamily().isEmpty();
    }
}

/* Operating System list

Unsupported:
AIX
Digital Unix
FreeBSD
HP UX
Irix
Mac OS
Mac OS X
MPE/iX
Netware 4.11
OS/2
Solaris

Supported:
Windows 2000
Windows 7
Windows 95
Windows 98
Windows NT
Windows Vista
Windows XP
Linux
 * */
