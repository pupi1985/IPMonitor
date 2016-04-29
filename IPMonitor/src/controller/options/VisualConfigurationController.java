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

import java.awt.event.*;
import javax.swing.*;
import model.notification.configuration.*;
import controller.extras.*;
import view.options.*;

public class VisualConfigurationController {

    private VisualConfigurationView visualConfigurationView;

    public VisualConfigurationController(JDialog owner) {
        visualConfigurationView = new VisualConfigurationView(owner);
        visualConfigurationView.getJButtonInfo().addActionListener(
                new InfoActionListener("Tips for Title and Text", false));
        visualConfigurationView.getJButtonOk().addActionListener(
                new JButtonOkAction());
        visualConfigurationView.getJButtonApply().addActionListener(
                new JButtonApplyAction());
        visualConfigurationView.getJButtonCancel().addActionListener(
                new JButtonCancelAction());
        visualConfigurationView.getRootPane().registerKeyboardAction(
                visualConfigurationView.getJButtonCancel().getActionListeners()[0],
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
        visualConfigurationView.setVisible(true);
    }

    private void setTitle() {
        VisualConfiguration.getInstance().setTitle(
                visualConfigurationView.getJTextFieldTitle().getText());
    }

    private void setText() {
        VisualConfiguration.getInstance().setText(
                visualConfigurationView.getJTextAreaText().getText());
    }

    private void setIcon() {
        VisualConfiguration.getInstance().setIcon(
                ((MessageTypeWrapper) visualConfigurationView.getJComboBoxIcons().getSelectedItem()).getMessageType());
    }

    private class JButtonApplyAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            setTitle();
            setText();
            setIcon();
        }
    }

    private class JButtonOkAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            new JButtonApplyAction().actionPerformed(null);
            visualConfigurationView.dispose();
        }
    }

    private class JButtonCancelAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            visualConfigurationView.dispose();
        }
    }
}
