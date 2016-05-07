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

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;

import controller.extras.InfoActionListener;
import model.notification.configuration.AudioConfiguration;
import model.notification.configuration.CommandConfiguration;
import view.options.CommandConfigurationView;

public class CommandConfigurationController {

    private CommandConfigurationView commandConfigurationView;

    public CommandConfigurationController(JDialog owner) {
        ActionListener cancelAction = new CancelAction();

        commandConfigurationView = new CommandConfigurationView(owner);
        commandConfigurationView.getJButtonInfo()
                .addActionListener(new InfoActionListener("Tips for the command", false));
        commandConfigurationView.getJButtonOk().addActionListener(new OkAction());
        commandConfigurationView.getJButtonCancel().addActionListener(cancelAction);
        commandConfigurationView.getJButtonBrowse().addActionListener(new BrowseAction());
        commandConfigurationView.getRootPane().registerKeyboardAction(cancelAction,
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        commandConfigurationView.setVisible(true);
    }

    private class OkAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            CommandConfiguration.getInstance().setCommand(commandConfigurationView.getJTextFieldFilePath().getText());
            commandConfigurationView.dispose();
        }
    }

    private class CancelAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            commandConfigurationView.dispose();
        }
    }

    private class BrowseAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            JFileChooser jFileChooser = new JFileChooser(AudioConfiguration.getInstance().getFileName());
            if (jFileChooser.showOpenDialog(commandConfigurationView) == JFileChooser.APPROVE_OPTION) {
                commandConfigurationView.getJTextFieldFilePath().setText(jFileChooser.getSelectedFile().getPath());
            }
        }
    }
}
