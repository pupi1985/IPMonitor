/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package controller.options;

import java.awt.event.*;
import java.io.File;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import model.notification.configuration.*;
import view.options.*;

public class AudioConfigurationController {

    private AudioConfigurationView audioConfigurationView;

    public AudioConfigurationController(JDialog owner) {
        audioConfigurationView = new AudioConfigurationView(owner);
        audioConfigurationView.getJButtonOk().addActionListener(
                new JButtonOkAction());
        audioConfigurationView.getJButtonCancel().addActionListener(
                new JButtonCancelAction());
        audioConfigurationView.getJButtonBrowse().addActionListener(
                new JButtonBrowseAction());
        audioConfigurationView.getRootPane().registerKeyboardAction(
                audioConfigurationView.getJButtonCancel().getActionListeners()[0],
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
        audioConfigurationView.setVisible(true);
    }

    private class JButtonOkAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            AudioConfiguration.getInstance().setFileName(
                    audioConfigurationView.getJTextFieldFilePath().getText());
            audioConfigurationView.dispose();
        }
    }

    private class JButtonCancelAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            audioConfigurationView.dispose();
        }
    }

    private class JButtonBrowseAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            JFileChooser jFileChooser = new JFileChooser(AudioConfiguration.getInstance().getFileName());
            jFileChooser.setFileFilter(new SoundFileFilter());
            jFileChooser.setAcceptAllFileFilterUsed(false);
            if (jFileChooser.showOpenDialog(audioConfigurationView) == JFileChooser.APPROVE_OPTION) {
                audioConfigurationView.getJTextFieldFilePath().setText(
                        jFileChooser.getSelectedFile().getPath());
            }
        }
    }

    private class SoundFileFilter extends FileFilter {

        private HashSet<String> extensions = new HashSet<String>(Arrays.asList(
                "wav", "aiff", "au", "mid", "rmf"));

        public boolean accept(File file) {
            return extensions.contains(getExtension(file)) || file.isDirectory();
        }

        public String getDescription() {
            return "Audio files (*.wav, *.aiff, *.au, *.mid, *.rmf)";
        }

        private String getExtension(File file) {
            int index = file.getName().lastIndexOf('.');
            if (index > 0 && index < file.getName().length() - 1) {
                return file.getName().substring(index + 1).toLowerCase();
            } else {
                return null;
            }
        }
    }
}
