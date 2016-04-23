/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package view.options.panels;

import javax.swing.*;
import model.logger.*;

public class JPanelOptionsLogging extends javax.swing.JPanel {

    public JPanelOptionsLogging() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxEnableLogging = new javax.swing.JCheckBox();
        jPanelLoggingConfiguration = new javax.swing.JPanel();
        javax.swing.JLabel jLabelDaysToKeepLogs = new javax.swing.JLabel();
        jTextFieldDaysToKeepLogs = new javax.swing.JTextField();
        javax.swing.JLabel jLabelDescription = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jCheckBoxEnableLogging.setSelected(model.logger.MainLogger.getInstance().isEnabled());
        jCheckBoxEnableLogging.setText("Enable logging");

        jPanelLoggingConfiguration.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Logging configuration"));

        jLabelDaysToKeepLogs.setText("Number of log files to keep:");

        jTextFieldDaysToKeepLogs.setColumns(2);
        jTextFieldDaysToKeepLogs.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldDaysToKeepLogs.setText(String.valueOf(model.logger.MainLogger.getInstance().getMaxDaysToKeepLogs()));

        jLabelDescription.setText("NOTE: Log files are saved daily."); // NOI18N

        jLabel1.setText("New files will replace older files.");

        javax.swing.GroupLayout jPanelLoggingConfigurationLayout = new javax.swing.GroupLayout(jPanelLoggingConfiguration);
        jPanelLoggingConfiguration.setLayout(jPanelLoggingConfigurationLayout);
        jPanelLoggingConfigurationLayout.setHorizontalGroup(
            jPanelLoggingConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLoggingConfigurationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLoggingConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLoggingConfigurationLayout.createSequentialGroup()
                        .addComponent(jLabelDaysToKeepLogs)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDaysToKeepLogs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelDescription)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelLoggingConfigurationLayout.setVerticalGroup(
            jPanelLoggingConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLoggingConfigurationLayout.createSequentialGroup()
                .addGroup(jPanelLoggingConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDaysToKeepLogs)
                    .addComponent(jTextFieldDaysToKeepLogs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelLoggingConfiguration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBoxEnableLogging))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBoxEnableLogging)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelLoggingConfiguration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBoxEnableLogging;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanelLoggingConfiguration;
    private javax.swing.JTextField jTextFieldDaysToKeepLogs;
    // End of variables declaration//GEN-END:variables
    public JCheckBox getJCheckBoxEnableLogging() {
        return jCheckBoxEnableLogging;
    }

    public JTextField getJTextFieldDaysToKeepLogs() {
        return jTextFieldDaysToKeepLogs;
    }

    public JPanel getJPanelLoggingConfiguration() {
        return jPanelLoggingConfiguration;
    }
}
