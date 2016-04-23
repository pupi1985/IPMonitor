/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package view.options;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import model.notification.configuration.AudioConfiguration;

public class AudioConfigurationView extends JDialog {
	
	private javax.swing.JButton jButtonBrowse;
	private view.panels.JPanelConfirmation jPanelConfirmation;
	private javax.swing.JTextField jTextFieldFilePath;

    public AudioConfigurationView() {
        initComponents();
    }

    public AudioConfigurationView(JDialog owner) {
        super(owner, "Audio notification configuration", true);
        initComponents();
        getRootPane().setDefaultButton(getJButtonOk());
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        javax.swing.JPanel jPanelCenter = new javax.swing.JPanel();
        javax.swing.JPanel jPanelCenterBorder = new javax.swing.JPanel();
        jTextFieldFilePath = new javax.swing.JTextField();
        jButtonBrowse = new javax.swing.JButton();
        jPanelConfirmation = new view.panels.JPanelConfirmation(false);

        setResizable(false);

        jPanelCenterBorder.setBorder(javax.swing.BorderFactory.createTitledBorder("Select a file to play"));

        jTextFieldFilePath.setText(AudioConfiguration.getInstance().getFileName());

        jButtonBrowse.setText("Browse...");

        javax.swing.GroupLayout jPanelCenterBorderLayout = new javax.swing.GroupLayout(jPanelCenterBorder);
        jPanelCenterBorder.setLayout(jPanelCenterBorderLayout);
        jPanelCenterBorderLayout.setHorizontalGroup(
            jPanelCenterBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCenterBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldFilePath, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBrowse)
                .addContainerGap())
        );
        jPanelCenterBorderLayout.setVerticalGroup(
            jPanelCenterBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCenterBorderLayout.createSequentialGroup()
                .addGroup(jPanelCenterBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBrowse)
                    .addComponent(jTextFieldFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
