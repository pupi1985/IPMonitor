/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.extras;

import java.awt.*;
import java.text.*;
import java.util.*;

public class AboutInformation {

    private static AboutInformation instance;

    public static AboutInformation getInstance() {
        if (instance == null) {
            instance = new AboutInformation();
        }
        return instance;
    }

    private AboutInformation() {
    }

    public Image getImage() {
        return Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/tipmonitor32.png"));
    }

    public String getDeveloper() {
        return "Gabriel Zanetti";
    }

    public String getName() {
        return "IP Monitor";
    }

    public String getVersion() {
        return "0.04";
    }

    public Date getDate() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse("2010-03-27");
        } catch (Exception e) {
            return null;
        }
    }

    public String getUrl() {
        return "http://www.ip-monitor.com.ar";
    }

    public String getVisualUrl() {
        return "www.ip-monitor.com.ar";
    }
}
