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

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.mail.internet.AddressException;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import controller.extras.InfoActionListener;
import model.notification.configuration.MailConfiguration;
import view.options.MailConfigurationView;

public class MailConfigurationController {

    private MailConfigurationView mailConfigurationView;

    public MailConfigurationController(JDialog owner) {
        ActionListener cancelAction = new CancelAction();

        mailConfigurationView = new MailConfigurationView(owner);
        mailConfigurationView.getJButtonInfo()
                .addActionListener(new InfoActionListener("Tips for Subject and Body", false));
        mailConfigurationView.getJButtonOk().addActionListener(new OkAction());
        mailConfigurationView.getJButtonCancel().addActionListener(cancelAction);
        mailConfigurationView.getJButtonApply().addActionListener(new ApplyAction());
        mailConfigurationView.getJCheckBoxRequiresAuthentication()
                .addActionListener(new JCheckboxRequiresAuthenticationAction());
        mailConfigurationView.getRootPane().registerKeyboardAction(cancelAction,
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        setEnableRequiresAuthentication();
        mailConfigurationView.setVisible(true);
    }

    private void setServer() {
        MailConfiguration.getInstance().setHost(mailConfigurationView.getJTextFieldServer().getText());
    }

    private void setPort() throws NumberFormatException {
        int port;
        try {
            port = Integer.valueOf(mailConfigurationView.getJTextFieldPort().getText());
            MailConfiguration.getInstance().setPort(port);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Port is not correct. Please enter a number between 1 and 65535.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            mailConfigurationView.getJTextFieldPort().requestFocus();
            throw e;
        }
    }

    private void setUser() {
        MailConfiguration.getInstance().setUser(mailConfigurationView.getJTextFieldUser().getText());
    }

    private void setPassword() {
        MailConfiguration.getInstance()
                .setPassword(String.valueOf(mailConfigurationView.getJPasswordFieldPassword().getPassword()));
    }

    private void setRequiresAuthentication() {
        MailConfiguration.getInstance()
                .setAuthenticationRequired(mailConfigurationView.getJCheckBoxRequiresAuthentication().isSelected());
    }

    private void setUseSSL() {
        MailConfiguration.getInstance().setSSL(mailConfigurationView.getJCheckBoxUseSSL().isSelected());
    }

    private void setFromName() {
        MailConfiguration.getInstance().setFromName(mailConfigurationView.getJTextFieldFromName().getText());
    }

    private void setFromAddress() throws AddressException {
        try {
            MailConfiguration.getInstance()
                    .setFromEmailAddress(mailConfigurationView.getJTextFieldFromAddress().getText());
        } catch (AddressException e) {
            JOptionPane.showMessageDialog(null, "The address you want to send the mail from is not correct.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            mailConfigurationView.getJTextFieldFromAddress().requestFocus();
            throw e;
        }
    }

    private void setToAddresses() throws AddressException {
        try {
            MailConfiguration.getInstance().setToAddresses(mailConfigurationView.getJTextFieldToAddresses().getText());
        } catch (AddressException e) {
            JOptionPane.showMessageDialog(null, "The addresses you want to send the mail to are not correct.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            mailConfigurationView.getJTextFieldToAddresses().requestFocus();
            throw e;
        }
    }

    private void setSubject() {
        MailConfiguration.getInstance().setSubject(mailConfigurationView.getJTextFieldSubject().getText());
    }

    private void setText() {
        MailConfiguration.getInstance().setText(mailConfigurationView.getJTextAreaMessage().getText());
    }

    private void setUseHTML() {
        MailConfiguration.getInstance().setHTML(mailConfigurationView.getJCheckBoxUseHTML().isSelected());
    }

    private void setEnableRequiresAuthentication() {
        boolean enabled = mailConfigurationView.getJCheckBoxRequiresAuthentication().isSelected();
        JPanel authenticationPanel = mailConfigurationView.getJPanelAuthentication();
        authenticationPanel.setEnabled(enabled);
        Component[] components = authenticationPanel.getComponents();
        for (Component component : components) {
            component.setEnabled(enabled);
        }
    }

    private class OkAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            ApplyAction applyAction = new ApplyAction();
            applyAction.actionPerformed(null);
            if (applyAction.isEverythingOk()) {
                mailConfigurationView.dispose();
            }
        }
    }

    private class CancelAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            mailConfigurationView.dispose();
        }
    }

    private class ApplyAction implements ActionListener {

        private boolean isEveryhingOk;

        public void actionPerformed(ActionEvent event) {
            try {
                setServer();
                setPort();
                setUser();
                setPassword();
                setRequiresAuthentication();
                setUseSSL();
                setFromName();
                setFromAddress();
                setToAddresses();
                setSubject();
                setText();
                setUseHTML();
                isEveryhingOk = true;
            } catch (Exception e) {
                isEveryhingOk = false;
            }
        }

        public boolean isEverythingOk() {
            return isEveryhingOk;
        }
    }

    private class JCheckboxRequiresAuthenticationAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            setEnableRequiresAuthentication();
        }
    }
}
