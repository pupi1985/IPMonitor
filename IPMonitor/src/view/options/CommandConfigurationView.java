/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package view.options;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import model.notification.configuration.CommandConfiguration;

public class CommandConfigurationView extends JDialog {
	
    private javax.swing.JButton jButtonBrowse;
    private view.extras.JButtonInfo jButtonInfo;
    private view.panels.JPanelConfirmation jPanelConfirmation;
    private javax.swing.JTextField jTextFieldFilePath;

    public CommandConfigurationView() {
        initComponents();
    }

    public CommandConfigurationView(JDialog owner) {
        super(owner, "Command notification configuration", true);
        initComponents();
        getRootPane().setDefaultButton(getJButtonOk());
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        javax.swing.JPanel jPanelCenter = new javax.swing.JPanel();
        javax.swing.JPanel jPanelCenterBorder = new javax.swing.JPanel();
        jTextFieldFilePath = new javax.swing.JTextField();
        jButtonBrowse = new javax.swing.JButton();
        jButtonInfo = new view.extras.JButtonInfo();
        jPanelConfirmation = new view.panels.JPanelConfirmation(false);

        setResizable(false);

        jPanelCenterBorder.setBorder(javax.swing.BorderFactory.createTitledBorder("Enter a command to execute"));

        jTextFieldFilePath.setText(CommandConfiguration.getInstance().getCommand());

        jButtonBrowse.setText("Browse...");

        javax.swing.GroupLayout jPanelCenterBorderLayout = new javax.swing.GroupLayout(jPanelCenterBorder);
        jPanelCenterBorder.setLayout(jPanelCenterBorderLayout);
        jPanelCenterBorderLayout.setHorizontalGroup(
            jPanelCenterBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCenterBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldFilePath, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBrowse)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelCenterBorderLayout.setVerticalGroup(
            jPanelCenterBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCenterBorderLayout.createSequentialGroup()
                .addGroup(jPanelCenterBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBrowse)
                    .addComponent(jTextFieldFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelCenterLayout = new javax.swing.GroupLayout(jPanelCenter);
        jPanelCenter.setLayout(jPanelCenterLayout);
        jPanelCenterLayout.setHorizontalGroup(
            jPanelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCenterBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelCenterLayout.setVerticalGroup(
            jPanelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCenterBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanelCenter, java.awt.BorderLayout.CENTER);
        getContentPane().add(jPanelConfirmation, java.awt.BorderLayout.SOUTH);

        pack();
    }

    public JButton getJButtonInfo() {
        return jButtonInfo;
    }

    public JButton getJButtonBrowse() {
        return jButtonBrowse;
    }

    public JTextField getJTextFieldFilePath() {
        return jTextFieldFilePath;
    }

    public JButton getJButtonOk() {
        return jPanelConfirmation.getJButtonOk();
    }

    public JButton getJButtonCancel() {
        return jPanelConfirmation.getJButtonCancel();
    }
}
