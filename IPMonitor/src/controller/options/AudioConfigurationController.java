/*
 * IP Monitor is a simple application which monitors your public IP
 * address for changes and lets you set different kinds of notification
 * such as email, audio, pop up or executing a command. It can also run
 * in background as a Windows service or Linux/Mac daemon.
 *
 * Copyright (C) 2007 - 2016  Gabriel Zanetti http://github.com/pupi1985
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package controller.options;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;

import model.notification.configuration.AudioConfiguration;
import view.options.AudioConfigurationView;

public class AudioConfigurationController {

    private AudioConfigurationView audioConfigurationView;

    public AudioConfigurationController(JDialog owner) {
        ActionListener cancelAction = new CancelAction();

        audioConfigurationView = new AudioConfigurationView(owner);
        audioConfigurationView.getJButtonOk().addActionListener(new OkAction());
        audioConfigurationView.getJButtonCancel().addActionListener(cancelAction);
        audioConfigurationView.getJButtonBrowse().addActionListener(new BrowseAction());
        audioConfigurationView.getRootPane().registerKeyboardAction(cancelAction,
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        audioConfigurationView.setVisible(true);
    }

    private class OkAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            AudioConfiguration.getInstance().setFileName(audioConfigurationView.getJTextFieldFilePath().getText());
            audioConfigurationView.dispose();
        }
    }

    private class CancelAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            audioConfigurationView.dispose();
        }
    }

    private class BrowseAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            JFileChooser jFileChooser = new JFileChooser(AudioConfiguration.getInstance().getFileName());
            jFileChooser.setFileFilter(new SoundFileFilter());
            jFileChooser.setAcceptAllFileFilterUsed(false);
            if (jFileChooser.showOpenDialog(audioConfigurationView) == JFileChooser.APPROVE_OPTION) {
                audioConfigurationView.getJTextFieldFilePath().setText(jFileChooser.getSelectedFile().getPath());
            }
        }
    }

    private class SoundFileFilter extends FileFilter {

        private HashSet<String> extensions = new HashSet<String>(Arrays.asList("wav", "aiff", "au", "mid", "rmf"));

        public boolean accept(File file) {
            return extensions.contains(getExtension(file)) || file.isDirectory();
        }

        public String getDescription() {
            return "Audio files (*.wav, *.aiff, *.au, *.mid, *.rmf)";
        }

        private String getExtension(File file) {
            int index = file.getName().lastIndexOf('.');
            if (index > 0 && index < file.getName().length() - 1) {
                return file.getName().substring(index + 1).toLowerCase(Locale.ENGLISH);
            } else {
                return null;
            }
        }
    }
}
