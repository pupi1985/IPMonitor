/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.notification.extras;

import java.io.*;

public class StreamReaderThread extends Thread {

    private StringBuffer stringBuffer;
    private InputStreamReader inputStreamReader;

    public StreamReaderThread(InputStream in, StringBuffer out) {
        stringBuffer = out;
        inputStreamReader = new InputStreamReader(in);
    }

    public void run() {
        int charNumber;
        try {
            while ((charNumber = inputStreamReader.read()) != -1)
                stringBuffer.append((char) charNumber);
        } catch (Exception e) {
            stringBuffer.append("\n" + e.getMessage());
        }
    }
}
