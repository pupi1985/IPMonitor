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
import javax.swing.KeyStroke;

import controller.extras.InfoActionListener;
import controller.extras.MessageTypeWrapper;
import model.notification.configuration.VisualConfiguration;
import view.options.VisualConfigurationView;

public class VisualConfigurationController {

    private VisualConfigurationView visualConfigurationView;

    public VisualConfigurationController(JDialog owner) {
        ActionListener cancelAction = new CancelAction();

        visualConfigurationView = new VisualConfigurationView(owner);
        visualConfigurationView.getJButtonInfo()
                .addActionListener(new InfoActionListener("Tips for Title and Text", false));
        visualConfigurationView.getJButtonOk().addActionListener(new OkAction());
        visualConfigurationView.getJButtonApply().addActionListener(new ApplyAction());
        visualConfigurationView.getJButtonCancel().addActionListener(cancelAction);
        visualConfigurationView.getRootPane().registerKeyboardAction(cancelAction,
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        visualConfigurationView.setVisible(true);
    }

    private void setTitle() {
        VisualConfiguration.getInstance().setTitle(visualConfigurationView.getJTextFieldTitle().getText());
    }

    private void setText() {
        VisualConfiguration.getInstance().setText(visualConfigurationView.getJTextAreaText().getText());
    }

    private void setIcon() {
        VisualConfiguration.getInstance().setIcon(
                ((MessageTypeWrapper) visualConfigurationView.getJComboBoxIcons().getSelectedItem()).getMessageType());
    }

    private class ApplyAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            setTitle();
            setText();
            setIcon();
        }
    }

    private class OkAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            new ApplyAction().actionPerformed(null);
            visualConfigurationView.dispose();
        }
    }

    private class CancelAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            visualConfigurationView.dispose();
        }
    }
}
