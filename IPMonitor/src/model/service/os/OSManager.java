/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.service.os;

import java.util.*;

public class OSManager {

    private static OSManager instance;
    private ArrayList<Class> idsOS;

    /* The order should not be changed because the index is used to store the
     * selected OS in the cfg file
     */
    private OSManager() {
        idsOS = new ArrayList<Class>();
        idsOS.add(Other.class);
        idsOS.add(Windows.class);
        idsOS.add(Ubuntu.class);
        idsOS.add(Debian.class);
    }

    public static OSManager getInstance() {
        if (instance == null) {
            instance = new OSManager();
        }
        return instance;
    }

    public Class getClassForID(int idOS) {
        try {
            return idsOS.get(idOS);
        } catch (Exception e) {
            return null;
        }
    }

    public int getIdForClass(Class OSClass) {
        return idsOS.indexOf(OSClass);
    }

    private boolean isOS(String OS) {
        return System.getProperty("os.name").toUpperCase().startsWith(OS);
    }

    public boolean isOSFamilyWindows() {
        return isOS("WINDOWS");
    }

    public boolean isOSFamilyLinux() {
        return isOS("LINUX");
    }

    public boolean isArchX86() {
        String arch = System.getProperty("os.arch");
        return arch.equals("x86") || arch.equals("i386") || arch.equals("i486") || arch.equals("i586") ||
                arch.equals("i686") || arch.equals("x86_64") || arch.equals("amd64") || arch.equals("athlon");
    }
}
