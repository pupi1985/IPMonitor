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

package controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import javax.swing.event.MouseInputAdapter;

import model.extras.AboutInformation;
import view.AboutView;

public class AboutController {

    private AboutView aboutView;
    private AboutInformation aboutInformation;

    public AboutController(JFrame owner) {
        ActionListener okAction = new OkAction();
        aboutInformation = new AboutInformation();

        aboutView = new AboutView(owner, aboutInformation);
        aboutView.getJButtonOk().addActionListener(okAction);
        aboutView.getJLabelProjectLinkField().addMouseListener(new JLabelProjectLinkMouseListener());
        aboutView.getJLabelDonateLinkField().addMouseListener(new JLabelDonateLinkMouseListener());
        aboutView.getRootPane().registerKeyboardAction(okAction, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
        aboutView.getRootPane().setDefaultButton(aboutView.getJButtonOk());
        aboutView.setVisible(true);
    }

    private class OkAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            aboutView.dispose();
        }
    }

    private class JLabelProjectLinkMouseListener extends MouseInputAdapter {

        public void mouseClicked(MouseEvent event) {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI(aboutInformation.getProjectLinkUrl()));
                } catch (Exception e) {
                }
            }
        }

        public void mouseEntered(MouseEvent event) {
            aboutView.getJLabelProjectLinkField().setText("<html><u>" + aboutInformation.getProjectLinkText() + "</u></html>");
        }

        public void mouseExited(MouseEvent event) {
            aboutView.getJLabelProjectLinkField().setText(aboutInformation.getProjectLinkText());
        }
    }

    private class JLabelDonateLinkMouseListener extends MouseInputAdapter {

        public void mouseClicked(MouseEvent event) {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI(aboutInformation.getDonateLinkUrl()));
                } catch (Exception e) {
                }
            }
        }

        public void mouseEntered(MouseEvent event) {
            aboutView.getJLabelDonateLinkField().setText("<html><u>" + aboutInformation.getDonateLinkText() + "</u></html>");
        }

        public void mouseExited(MouseEvent event) {
            aboutView.getJLabelDonateLinkField().setText(aboutInformation.getDonateLinkText());
        }
    }
}
