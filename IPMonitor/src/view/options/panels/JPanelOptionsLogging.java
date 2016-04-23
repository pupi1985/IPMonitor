/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package view.options.panels;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

public class JPanelOptionsLogging extends JPanel {

	private JCheckBox jCheckBoxEnableLogging;
	private JLabel jLabel1;
	private JPanel jPanelLoggingConfiguration;
	private JTextField jTextFieldDaysToKeepLogs;

    public JPanelOptionsLogging() {
        initComponents();
    }

    private void initComponents() {
        jCheckBoxEnableLogging = new JCheckBox();
        jPanelLoggingConfiguration = new JPanel();
        JLabel jLabelDaysToKeepLogs = new JLabel();
        jTextFieldDaysToKeepLogs = new JTextField();
        JLabel jLabelDescription = new JLabel();
        jLabel1 = new JLabel();

        jCheckBoxEnableLogging.setSelected(model.logger.MainLogger.getInstance().isEnabled());
        jCheckBoxEnableLogging.setText("Enable logging");

        jPanelLoggingConfiguration.setBorder(BorderFactory.createTitledBorder(null, "Logging configuration"));

        jLabelDaysToKeepLogs.setText("Number of log files to keep:");

        jTextFieldDaysToKeepLogs.setColumns(2);
        jTextFieldDaysToKeepLogs.setHorizontalAlignment(JTextField.RIGHT);
        jTextFieldDaysToKeepLogs.setText(String.valueOf(model.logger.MainLogger.getInstance().getMaxDaysToKeepLogs()));

        jLabelDescription.setText("NOTE: Log files are saved daily."); // NOI18N

        jLabel1.setText("New files will replace older files.");

        GroupLayout jPanelLoggingConfigurationLayout = new GroupLayout(jPanelLoggingConfiguration);
        jPanelLoggingConfiguration.setLayout(jPanelLoggingConfigurationLayout);
        jPanelLoggingConfigurationLayout.setHorizontalGroup(
            jPanelLoggingConfigurationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLoggingConfigurationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLoggingConfigurationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLoggingConfigurationLayout.createSequentialGroup()
                        .addComponent(jLabelDaysToKeepLogs)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDaysToKeepLogs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelDescription)
                    .addComponent(jLabel1))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelLoggingConfigurationLayout.setVerticalGroup(
            jPanelLoggingConfigurationLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLoggingConfigurationLayout.createSequentialGroup()
                .addGroup(jPanelLoggingConfigurationLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDaysToKeepLogs)
                    .addComponent(jTextFieldDaysToKeepLogs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelDescription)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelLoggingConfiguration, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBoxEnableLogging))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBoxEnableLogging)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelLoggingConfiguration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }

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
