/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package controller;

import model.extras.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.net.*;
import view.*;

public class AboutController {

    private AboutView aboutView;

    public AboutController(JFrame owner) {
        aboutView = new AboutView(owner);
        aboutView.getJButtonOk().addActionListener(new JButtonOkAction());
        aboutView.getJLabelURLField().addMouseListener(
                new JLabelUrlMouseListener());
        aboutView.getRootPane().registerKeyboardAction(
                aboutView.getJButtonOk().getActionListeners()[0],
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
        aboutView.getRootPane().setDefaultButton(aboutView.getJButtonOk());
        aboutView.setVisible(true);
    }

    private class JButtonOkAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            aboutView.dispose();
        }
    }

    private class JLabelUrlMouseListener implements MouseInputListener {

        public void mouseClicked(MouseEvent event) {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(
                            new URI(AboutInformation.getInstance().getUrl()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public void mouseEntered(MouseEvent event) {
            aboutView.getJLabelURLField().setText(
                    "<html><u>" + AboutInformation.getInstance().getVisualUrl() + "</u></html>");
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
