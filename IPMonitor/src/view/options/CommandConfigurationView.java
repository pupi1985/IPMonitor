/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package view.options;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import model.notification.configuration.CommandConfiguration;
import view.extras.JButtonInfo;

public class CommandConfigurationView extends JDialog {
	
    private JButton jButtonBrowse;
    private JButtonInfo jButtonInfo;
    private view.panels.JPanelConfirmation jPanelConfirmation;
    private JTextField jTextFieldFilePath;

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
        JPanel jPanelCenter = new JPanel();
        JPanel jPanelCenterBorder = new JPanel();
        jTextFieldFilePath = new JTextField();
        jButtonBrowse = new JButton();
        jButtonInfo = new JButtonInfo();
        jPanelConfirmation = new view.panels.JPanelConfirmation(false);

        setResizable(false);

        jPanelCenterBorder.setBorder(BorderFactory.createTitledBorder("Enter a command to execute"));

        jTextFieldFilePath.setText(CommandConfiguration.getInstance().getCommand());

        jButtonBrowse.setText("Browse...");

        GroupLayout jPanelCenterBorderLayout = new GroupLayout(jPanelCenterBorder);
        jPanelCenterBorder.setLayout(jPanelCenterBorderLayout);
        jPanelCenterBorderLayout.setHorizontalGroup(
            jPanelCenterBorderLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCenterBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldFilePath, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBrowse)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelCenterBorderLayout.setVerticalGroup(
            jPanelCenterBorderLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCenterBorderLayout.createSequentialGroup()
                .addGroup(jPanelCenterBorderLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBrowse)
                    .addComponent(jTextFieldFilePath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout jPanelCenterLayout = new GroupLayout(jPanelCenter);
        jPanelCenter.setLayout(jPanelCenterLayout);
        jPanelCenterLayout.setHorizontalGroup(
            jPanelCenterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCenterBorder, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelCenterLayout.setVerticalGroup(
            jPanelCenterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCenterBorder, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanelCenter, BorderLayout.CENTER);
        getContentPane().add(jPanelConfirmation, BorderLayout.SOUTH);

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
