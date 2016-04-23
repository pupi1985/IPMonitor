/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package controller.options;

import controller.extras.*;
import java.awt.event.*;
import javax.swing.*;
import model.notification.configuration.*;
import view.options.*;

public class CommandConfigurationController {

    private CommandConfigurationView commandConfigurationView;

    public CommandConfigurationController(JDialog owner) {
        commandConfigurationView = new CommandConfigurationView(owner);
        commandConfigurationView.getJButtonInfo().addActionListener(
                new InfoActionListener("Tips for the command", false));
        commandConfigurationView.getJButtonOk().addActionListener(
                new JButtonOkAction());
        commandConfigurationView.getJButtonCancel().addActionListener(
                new JButtonCancelAction());
        commandConfigurationView.getJButtonBrowse().addActionListener(
                new JButtonBrowseAction());
        commandConfigurationView.getRootPane().registerKeyboardAction(
                commandConfigurationView.getJButtonCancel().getActionListeners()[0],
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
        commandConfigurationView.setVisible(true);
    }

    private class JButtonOkAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            CommandConfiguration.getInstance().setCommand(
                    commandConfigurationView.getJTextFieldFilePath().getText());
            commandConfigurationView.dispose();
        }
    }

    private class JButtonCancelAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            commandConfigurationView.dispose();
        }
    }

    private class JButtonBrowseAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            JFileChooser jFileChooser = new JFileChooser(AudioConfiguration.getInstance().getFileName());
            if (jFileChooser.showOpenDialog(commandConfigurationView) == JFileChooser.APPROVE_OPTION) {
                commandConfigurationView.getJTextFieldFilePath().setText(
                        jFileChooser.getSelectedFile().getPath());
            }
        }
    }
}
