/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.ipreader;

import java.io.*;
import java.net.*;
import java.util.regex.*;
import model.configuration.ConfigurationManager;
import model.ipreader.exceptions.*;

public class IPReader {

    private String url;
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) throws MalformedURLException {
        new URL(url);
        this.url = url;
    }

    private String readWebPage() throws IOException {
        String readString;
        String result = "";
        BufferedReader HTMLpage = new BufferedReader(new InputStreamReader(
                new URL(url).openStream()));
        while ((readString = HTMLpage.readLine()) != null) {
            result += readString;
        }
        return result;
    }

    public String getIP() throws IOException, IPNotFoundException {
        String text = readWebPage();
        String result = "";
        Pattern pattern = Pattern.compile(ConfigurationManager.getInstance().getIPPattern());
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            result = matcher.group();
        } else {
            throw new IPNotFoundException();
        }
        return result;
    }
}
