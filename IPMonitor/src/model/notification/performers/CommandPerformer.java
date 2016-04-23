/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.notification.performers;

import java.io.IOException;
import model.extras.InfoParser;
import model.notification.configuration.*;
import model.notification.extras.*;

public class CommandPerformer extends AbstractPerformer {

    private static CommandPerformer instance;

    private CommandPerformer() {
    }

    public static CommandPerformer getInstance() {
        if (instance == null) {
            instance = new CommandPerformer();
        }
        return instance;
    }

    public void executeCommand(String fromIP, String toIP) throws IOException, InterruptedException  {
        StringBuffer err = new StringBuffer();
        StringBuffer out = new StringBuffer();
        Process proc = Runtime.getRuntime().exec(
                InfoParser.getInstance().parseField(
                CommandConfiguration.getInstance().getCommand(), fromIP, toIP));

        StreamReaderThread outThread = new StreamReaderThread(proc.getInputStream(), out);
        StreamReaderThread errThread = new StreamReaderThread(proc.getErrorStream(), err);
        outThread.start();
        errThread.start();
        proc.waitFor();
        outThread.join();
        errThread.join();
        if (out.length() > 0) {
            System.out.println(out.toString());
        }
        if (err.length() > 0) {
            System.out.println(err.toString());
        }
    }
}
