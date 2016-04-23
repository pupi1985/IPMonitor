/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package view.options;

import javax.swing.*;
import model.notification.configuration.*;

public class MailConfigurationView extends JDialog {
    
    public MailConfigurationView(JDialog owner) {
        super(owner, true);
        initComponents();
        getRootPane().setDefaultButton(getJButtonOk());
        setLocationRelativeTo(null);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelCenter = new javax.swing.JPanel();
        javax.swing.JPanel jPanelClientConfiguration = new javax.swing.JPanel();
        javax.swing.JLabel jLabelServer = new javax.swing.JLabel();
        jTextFieldServer = new javax.swing.JTextField();
        javax.swing.JLabel jLabelPort = new javax.swing.JLabel();
        jTextFieldPort = new javax.swing.JTextField();
        jCheckBoxRequiresAuthentication = new javax.swing.JCheckBox();
        jPanelAuthentication = new javax.swing.JPanel();
        javax.swing.JLabel jLabelUser = new javax.swing.JLabel();
        jTextFieldUser = new javax.swing.JTextField();
        javax.swing.JLabel jLabelPassword = new javax.swing.JLabel();
        jCheckBoxUseSSL = new javax.swing.JCheckBox();
        jPasswordFieldPassword = new javax.swing.JPasswordField();
        javax.swing.JPanel jPanelMessage = new javax.swing.JPanel();
        javax.swing.JLabel jLabelFromName = new javax.swing.JLabel();
        jTextFieldFromName = new javax.swing.JTextField();
        javax.swing.JLabel jLabelFromAddress = new javax.swing.JLabel();
        jTextFieldFromAddress = new javax.swing.JTextField();
        javax.swing.JLabel jLabelToAddresses = new javax.swing.JLabel();
        jTextFieldToAddresses = new javax.swing.JTextField();
        javax.swing.JLabel jLabelSubject = new javax.swing.JLabel();
        jTextFieldSubject = new javax.swing.JTextField();
        javax.swing.JScrollPane jScrollPaneMessge = new javax.swing.JScrollPane();
        jTextAreaMessage = new javax.swing.JTextArea();
        jCheckBoxUseHTML = new javax.swing.JCheckBox();
        jButtonInfo = new view.extras.JButtonInfo();
        jPanelConfirmation = new view.panels.JPanelConfirmation(true, true);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Email notification configuration");

        jPanelClientConfiguration.setBorder(javax.swing.BorderFactory.createTitledBorder("Client configuration"));

        jLabelServer.setText("Server:");

        jTextFieldServer.setText(MailConfiguration.getInstance().getHost());

        jLabelPort.setText("Port:");

        jTextFieldPort.setColumns(5);
        jTextFieldPort.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldPort.setText(String.valueOf(MailConfiguration.getInstance().getPort()));

        jCheckBoxRequiresAuthentication.setSelected(MailConfiguration.getInstance().isAuthenticationRequired());
        jCheckBoxRequiresAuthentication.setText("Requires authentication");

        jPanelAuthentication.setBorder(javax.swing.BorderFactory.createTitledBorder("Authentication"));

        jLabelUser.setText("User:");

        jTextFieldUser.setText(MailConfiguration.getInstance().getUser());

        jLabelPassword.setText("Password:");

        jCheckBoxUseSSL.setSelected(MailConfiguration.getInstance().isSSL());
        jCheckBoxUseSSL.setText("Use SSL");

        jPasswordFieldPassword.setText(MailConfiguration.getInstance().getPassword());

        javax.swing.GroupLayout jPanelAuthenticationLayout = new javax.swing.GroupLayout(jPanelAuthentication);
        jPanelAuthentication.setLayout(jPanelAuthenticationLayout);
        jPanelAuthenticationLayout.setHorizontalGroup(
            jPanelAuthenticationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAuthenticationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAuthenticationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAuthenticationLayout.createSequentialGroup()
                        .addGroup(jPanelAuthenticationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelPassword)
                            .addComponent(jLabelUser))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelAuthenticationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldUser, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                            .addComponent(jPasswordFieldPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)))
                    .addComponent(jCheckBoxUseSSL))
                .addContainerGap())
        );
        jPanelAuthenticationLayout.setVerticalGroup(
            jPanelAuthenticationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAuthenticationLayout.createSequentialGroup()
                .addGroup(jPanelAuthenticationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUser)
                    .addComponent(jTextFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelAuthenticationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPassword)
                    .addComponent(jPasswordFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBoxUseSSL))
        );

        javax.swing.GroupLayout jPanelClientConfigurationLayout = new javax.swing.GroupLayout(jPanelClientConfiguration);
        jPanelClientConfiguration.setLayout(jPanelClientConfigurationLayout);
        jPanelClientConfigurationLayout.setHorizontalGroup(
            jPanelClientConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelClientConfigurationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelClientConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelAuthentication, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelClientConfigurationLayout.createSequentialGroup()
                        .addComponent(jLabelServer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldServer, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelPort)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBoxRequiresAuthentication, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanelClientConfigurationLayout.setVerticalGroup(
            jPanelClientConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClientConfigurationLayout.createSequentialGroup()
                .addGroup(jPanelClientConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPort)
                    .addComponent(jTextFieldServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelServer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxRequiresAuthentication)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelAuthentication, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelMessage.setBorder(javax.swing.BorderFactory.createTitledBorder("Message"));

        jLabelFromName.setText("From name:");

        jTextFieldFromName.setColumns(40);
        jTextFieldFromName.setText(MailConfiguration.getInstance().getFromName());

        jLabelFromAddress.setText("From address:");

        jTextFieldFromAddress.setColumns(40);
        jTextFieldFromAddress.setText(MailConfiguration.getInstance().getFromAddress());

        jLabelToAddresses.setText("To addresses:");

        jTextFieldToAddresses.setColumns(40);
        jTextFieldToAddresses.setText(MailConfiguration.getInstance().getToAddresses());

        jLabelSubject.setText("Subject:");

        jTextFieldSubject.setColumns(40);
        jTextFieldSubject.setText(MailConfiguration.getInstance().getSubject());

        jTextAreaMessage.setColumns(20);
        jTextAreaMessage.setFont(jTextFieldServer.getFont());
        jTextAreaMessage.setRows(6);
        jTextAreaMessage.setText(MailConfiguration.getInstance().getText());
        jScrollPaneMessge.setViewportView(jTextAreaMessage);

        jCheckBoxUseHTML.setSelected(MailConfiguration.getInstance().isHTML());
        jCheckBoxUseHTML.setText("Use HTML");

        javax.swing.GroupLayout jPanelMessageLayout = new javax.swing.GroupLayout(jPanelMessage);
        jPanelMessage.setLayout(jPanelMessageLayout);
        jPanelMessageLayout.setHorizontalGroup(
            jPanelMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMessageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneMessge, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                    .addGroup(jPanelMessageLayout.createSequentialGroup()
                        .addComponent(jCheckBoxUseHTML)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 271, Short.MAX_VALUE)
                        .addComponent(jButtonInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelMessageLayout.createSequentialGroup()
                        .addGroup(jPanelMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelFromAddress)
                            .addComponent(jLabelFromName)
                            .addComponent(jLabelToAddresses)
                            .addComponent(jLabelSubject))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldFromName)
                            .addComponent(jTextFieldSubject)
                            .addComponent(jTextFieldToAddresses)
                            .addComponent(jTextFieldFromAddress))))
                .addContainerGap())
        );
        jPanelMessageLayout.setVerticalGroup(
            jPanelMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMessageLayout.createSequentialGroup()
                .addGroup(jPanelMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFromName)
                    .addComponent(jTextFieldFromName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFromAddress)
                    .addComponent(jTextFieldFromAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelToAddresses)
                    .addComponent(jTextFieldToAddresses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSubject)
                    .addComponent(jTextFieldSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneMessge, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxUseHTML)
                    .addComponent(jButtonInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelCenterLayout = new javax.swing.GroupLayout(jPanelCenter);
        jPanelCenter.setLayout(jPanelCenterLayout);
        jPanelCenterLayout.setHorizontalGroup(
            jPanelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelMessage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelClientConfiguration, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelCenterLayout.setVerticalGroup(
            jPanelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelClientConfiguration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanelCenter, java.awt.BorderLayout.CENTER);
        getContentPane().add(jPanelConfirmation, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.extras.JButtonInfo jButtonInfo;
    private javax.swing.JCheckBox jCheckBoxRequiresAuthentication;
    private javax.swing.JCheckBox jCheckBoxUseHTML;
    private javax.swing.JCheckBox jCheckBoxUseSSL;
    private javax.swing.JPanel jPanelAuthentication;
    private javax.swing.JPanel jPanelCenter;
    private view.panels.JPanelConfirmation jPanelConfirmation;
    private javax.swing.JPasswordField jPasswordFieldPassword;
    private javax.swing.JTextArea jTextAreaMessage;
    private javax.swing.JTextField jTextFieldFromAddress;
    private javax.swing.JTextField jTextFieldFromName;
    private javax.swing.JTextField jTextFieldPort;
    private javax.swing.JTextField jTextFieldServer;
    private javax.swing.JTextField jTextFieldSubject;
    private javax.swing.JTextField jTextFieldToAddresses;
    private javax.swing.JTextField jTextFieldUser;
    // End of variables declaration//GEN-END:variables
    public JPanel getJPanelAuthentication() {
        return jPanelAuthentication;
    }

    public JButton getJButtonInfo() {
        return jButtonInfo;
    }

    public JCheckBox getJCheckBoxRequiresAuthentication() {
        return jCheckBoxRequiresAuthentication;
    }

    public JCheckBox getJCheckBoxUseHTML() {
        return jCheckBoxUseHTML;
    }

    public JCheckBox getJCheckBoxUseSSL() {
        return jCheckBoxUseSSL;
    }

    public JTextArea getJTextAreaMessage() {
        return jTextAreaMessage;
    }

    public JTextField getJTextFieldFromAddress() {
        return jTextFieldFromAddress;
    }

    public JTextField getJTextFieldFromName() {
        return jTextFieldFromName;
    }

    public JPasswordField getJPasswordFieldPassword() {
        return jPasswordFieldPassword;
    }

    public JTextField getJTextFieldPort() {
        return jTextFieldPort;
    }

    public JTextField getJTextFieldServer() {
        return jTextFieldServer;
    }

    public JTextField getJTextFieldSubject() {
        return jTextFieldSubject;
    }

    public JTextField getJTextFieldToAddresses() {
        return jTextFieldToAddresses;
    }

    public JTextField getJTextFieldUser() {
        return jTextFieldUser;
    }

    public JButton getJButtonOk() {
        return jPanelConfirmation.getJButtonOk();
    }

    public JButton getJButtonCancel() {
        return jPanelConfirmation.getJButtonCancel();
    }

    public JButton getJButtonApply() {
        return jPanelConfirmation.getJButtonApply();
    }
}
