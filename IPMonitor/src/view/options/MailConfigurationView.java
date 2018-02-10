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

package view.options;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import model.configuration.ConnectionSecurityType;
import model.notification.configuration.MailConfiguration;
import view.extras.JButtonInfo;
import view.panels.JPanelConfirmation;

public class MailConfigurationView extends JDialog {

    private JButtonInfo jButtonInfo;
    private JCheckBox jCheckBoxRequiresAuthentication;
    private JCheckBox jCheckBoxUseHTML;
    private JComboBox<ConnectionSecurityType> jComboBoxConnectionSecurity;
    private JPanel jPanelAuthentication;
    private JPanel jPanelCenter;
    private JPanelConfirmation jPanelConfirmation;
    private JPasswordField jPasswordFieldPassword;
    private JTextArea jTextAreaMessage;
    private JTextField jTextFieldFromAddress;
    private JTextField jTextFieldFromName;
    private JTextField jTextFieldPort;
    private JTextField jTextFieldServer;
    private JTextField jTextFieldSubject;
    private JTextField jTextFieldToAddresses;
    private JTextField jTextFieldUser;

    public MailConfigurationView(JDialog owner) {
        super(owner, true);
        initComponents();
        getRootPane().setDefaultButton(getJButtonOk());
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        jPanelCenter = new JPanel();
        JPanel jPanelClientConfiguration = new JPanel();

        JLabel jLabelServer = new JLabel();
        jTextFieldServer = new JTextField();

        JLabel jLabelPort = new JLabel();
        jTextFieldPort = new JTextField();

        jCheckBoxRequiresAuthentication = new JCheckBox();

        jPanelAuthentication = new JPanel();

        JLabel jLabelUser = new JLabel();
        jTextFieldUser = new JTextField();

        JLabel jLabelPassword = new JLabel();
        jPasswordFieldPassword = new JPasswordField();

        JLabel jLabelConnectionSecurity = new JLabel();
        jComboBoxConnectionSecurity = new JComboBox<>();
        jComboBoxConnectionSecurity.setModel(new DefaultComboBoxModel<ConnectionSecurityType>(ConnectionSecurityType.values()));

        JPanel jPanelMessage = new JPanel();

        JLabel jLabelFromName = new JLabel();
        jTextFieldFromName = new JTextField();

        JLabel jLabelFromAddress = new JLabel();
        jTextFieldFromAddress = new JTextField();

        JLabel jLabelToAddresses = new JLabel();
        jTextFieldToAddresses = new JTextField();

        JLabel jLabelSubject = new JLabel();
        jTextFieldSubject = new JTextField();

        JScrollPane jScrollPaneMessge = new JScrollPane();
        jTextAreaMessage = new JTextArea();

        jCheckBoxUseHTML = new JCheckBox();

        jButtonInfo = new JButtonInfo();

        jPanelConfirmation = new JPanelConfirmation(true, true, true);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Email notification configuration");

        jPanelClientConfiguration.setBorder(BorderFactory.createTitledBorder("Client configuration"));

        jLabelServer.setText("Server:");

        jTextFieldServer.setText(MailConfiguration.getInstance().getHost());

        jLabelPort.setText("Port:");

        jTextFieldPort.setColumns(5);
        jTextFieldPort.setHorizontalAlignment(JTextField.RIGHT);
        jTextFieldPort.setText(String.valueOf(MailConfiguration.getInstance().getPort()));

        jCheckBoxRequiresAuthentication.setSelected(MailConfiguration.getInstance().isAuthenticationRequired());
        jCheckBoxRequiresAuthentication.setText("Requires authentication");

        jPanelAuthentication.setBorder(BorderFactory.createTitledBorder("Authentication"));

        jLabelUser.setText("User:");

        jTextFieldUser.setText(MailConfiguration.getInstance().getUser());

        jLabelPassword.setText("Password:");

        jComboBoxConnectionSecurity.setSelectedItem(MailConfiguration.getInstance().getConnectionSecurity());
        jLabelConnectionSecurity.setText("Connection security:");

        jPasswordFieldPassword.setText(MailConfiguration.getInstance().getPassword());

        GroupLayout jPanelAuthenticationLayout = new GroupLayout(jPanelAuthentication);
        jPanelAuthentication.setLayout(jPanelAuthenticationLayout);
        jPanelAuthenticationLayout.setHorizontalGroup(
            jPanelAuthenticationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAuthenticationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAuthenticationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAuthenticationLayout.createSequentialGroup()
                        .addGroup(jPanelAuthenticationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelConnectionSecurity)
                            .addComponent(jLabelPassword)
                            .addComponent(jLabelUser))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelAuthenticationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldUser, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                            .addComponent(jPasswordFieldPassword, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                            .addComponent(jComboBoxConnectionSecurity, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)))
                    .addComponent(jComboBoxConnectionSecurity))
                .addContainerGap())
        );
        jPanelAuthenticationLayout.setVerticalGroup(
            jPanelAuthenticationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAuthenticationLayout.createSequentialGroup()
                .addGroup(jPanelAuthenticationLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUser)
                    .addComponent(jTextFieldUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelAuthenticationLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPassword)
                    .addComponent(jPasswordFieldPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelAuthenticationLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelConnectionSecurity)
                    .addComponent(jComboBoxConnectionSecurity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        );

        GroupLayout jPanelClientConfigurationLayout = new GroupLayout(jPanelClientConfiguration);
        jPanelClientConfiguration.setLayout(jPanelClientConfigurationLayout);
        jPanelClientConfigurationLayout.setHorizontalGroup(
            jPanelClientConfigurationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanelClientConfigurationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelClientConfigurationLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelAuthentication, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.LEADING, jPanelClientConfigurationLayout.createSequentialGroup()
                        .addComponent(jLabelServer)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldServer, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelPort)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBoxRequiresAuthentication, GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanelClientConfigurationLayout.setVerticalGroup(
            jPanelClientConfigurationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClientConfigurationLayout.createSequentialGroup()
                .addGroup(jPanelClientConfigurationLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPort)
                    .addComponent(jTextFieldServer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelServer))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxRequiresAuthentication)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelAuthentication, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelMessage.setBorder(BorderFactory.createTitledBorder("Message"));

        jLabelFromName.setText("From name:");

        jTextFieldFromName.setColumns(40);
        jTextFieldFromName.setText(MailConfiguration.getInstance().getFromName());

        jLabelFromAddress.setText("From address:");

        jTextFieldFromAddress.setColumns(40);
        jTextFieldFromAddress.setText(MailConfiguration.getInstance().getFromEmailAddress());

        jLabelToAddresses.setText("To addresses:");

        jTextFieldToAddresses.setColumns(40);
        jTextFieldToAddresses.setText(MailConfiguration.getInstance().getToAddresses());

        jLabelSubject.setText("Subject:");

        jTextFieldSubject.setColumns(40);
        jTextFieldSubject.setText(MailConfiguration.getInstance().getSubject());

        jTextAreaMessage.setRows(6);
        jTextAreaMessage.setColumns(20);
        jTextAreaMessage.setText(MailConfiguration.getInstance().getText());

        jScrollPaneMessge.setViewportView(jTextAreaMessage);

        jCheckBoxUseHTML.setSelected(MailConfiguration.getInstance().isHTML());
        jCheckBoxUseHTML.setText("Use HTML");

        GroupLayout jPanelMessageLayout = new GroupLayout(jPanelMessage);
        jPanelMessage.setLayout(jPanelMessageLayout);
        jPanelMessageLayout.setHorizontalGroup(
            jPanelMessageLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanelMessageLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanelMessageLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPaneMessge)
                        .addGroup(jPanelMessageLayout.createSequentialGroup()
                            .addComponent(jCheckBoxUseHTML)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelMessageLayout.createSequentialGroup()
                            .addGroup(jPanelMessageLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelFromAddress)
                                .addComponent(jLabelFromName)
                                .addComponent(jLabelToAddresses)
                                .addComponent(jLabelSubject))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanelMessageLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldFromName)
                                .addComponent(jTextFieldSubject)
                                .addComponent(jTextFieldToAddresses)
                                .addComponent(jTextFieldFromAddress))))
                    .addContainerGap())
        );
        jPanelMessageLayout.setVerticalGroup(
            jPanelMessageLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanelMessageLayout.createSequentialGroup()
                    .addGroup(jPanelMessageLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelFromName)
                        .addComponent(jTextFieldFromName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanelMessageLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelFromAddress)
                        .addComponent(jTextFieldFromAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanelMessageLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelToAddresses)
                        .addComponent(jTextFieldToAddresses, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanelMessageLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelSubject)
                        .addComponent(jTextFieldSubject, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPaneMessge)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanelMessageLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBoxUseHTML)
                        .addComponent(jButtonInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );

        GroupLayout jPanelCenterLayout = new GroupLayout(jPanelCenter);
        jPanelCenter.setLayout(jPanelCenterLayout);
        jPanelCenterLayout.setHorizontalGroup(
            jPanelCenterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanelCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCenterLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelMessage, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelClientConfiguration, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelCenterLayout.setVerticalGroup(
            jPanelCenterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelClientConfiguration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelMessage, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanelCenter, BorderLayout.CENTER);
        getContentPane().add(jPanelConfirmation, BorderLayout.SOUTH);

        pack();
    }

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

    public JComboBox<ConnectionSecurityType> getJComboBoxConnectionSecurity() {
        return jComboBoxConnectionSecurity;
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
