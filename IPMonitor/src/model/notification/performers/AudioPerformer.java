/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.notification.performers;

import java.io.*;
import java.net.*;
import java.applet.*;
import model.notification.configuration.*;

public class AudioPerformer extends AbstractPerformer {

    private static AudioPerformer instance;
    private AudioClip audioClip;

    private AudioPerformer() {
    }

    public static AudioPerformer getInstance() {
        if (instance == null) {
            instance = new AudioPerformer();
        }
        return instance;
    }

    public void play() throws MalformedURLException, FileNotFoundException {
        if (!new File(AudioConfiguration.getInstance().getFileName()).exists()) {
            throw new FileNotFoundException();
        }
        stop();
        audioClip = java.applet.Applet.newAudioClip(new java.net.URL("file:" + AudioConfiguration.getInstance().getFileName()));
        audioClip.play();
    }

    public void stop() {
        try {
            audioClip.stop();
        } catch (Exception e) {
            //No need to catch this
        }
    }
}
