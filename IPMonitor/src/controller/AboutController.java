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
import javax.swing.event.MouseInputListener;

import model.extras.AboutInformation;
import view.AboutView;

public class AboutController {

    private AboutView aboutView;

    public AboutController(JFrame owner) {
        ActionListener okAction = new OkAction();

        aboutView = new AboutView(owner);
        aboutView.getJButtonOk().addActionListener(okAction);
        aboutView.getJLabelURLField().addMouseListener(new JLabelUrlMouseListener());
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

    private class JLabelUrlMouseListener implements MouseInputListener {

        public void mouseClicked(MouseEvent event) {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI(AboutInformation.getInstance().getUrl()));
                } catch (Exception e) {
                }
            }
        }

        public void mouseEntered(MouseEvent event) {
            aboutView.getJLabelURLField()
                    .setText("<html><u>" + AboutInformation.getInstance().getVisualUrl() + "</u></html>");
        }

        public void mouseExited(MouseEvent event) {
            aboutView.getJLabelURLField().setText(AboutInformation.getInstance().getVisualUrl());
        }

        public void mousePressed(MouseEvent event) {
        }

        public void mouseReleased(MouseEvent event) {
        }

        public void mouseDragged(MouseEvent event) {
        }

        public void mouseMoved(MouseEvent event) {
        }
    }
}
