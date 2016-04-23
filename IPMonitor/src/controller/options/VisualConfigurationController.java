/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
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
