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
    private JPanel jPanelLoggingConfiguration;
    private JTextField jTextFieldDaysToKeepLogs;

    public JPanelOptionsLogging() {
        initComponents();
    }

    private void initComponents() {
        jCheckBoxEnableLogging = new JCheckBox();
        jPanelLoggingConfiguration = new JPanel();
        JLabel jLabelDaysToKeepLogs = new JLabel("Number of log files to keep:");
        jTextFieldDaysToKeepLogs = new JTextField();
        JLabel jLabelDescription = new JLabel(
                "<html>NOTE: Log files are saved daily.<br>New files will replace older files.</html>");

        jCheckBoxEnableLogging.setSelected(model.logger.MainLogger.getInstance().isEnabled());
        jCheckBoxEnableLogging.setText("Enable logging");

        jPanelLoggingConfiguration.setBorder(BorderFactory.createTitledBorder(null, "Logging configuration"));


        jTextFieldDaysToKeepLogs.setColumns(2);
        jTextFieldDaysToKeepLogs.setHorizontalAlignment(JTextField.RIGHT);
        jTextFieldDaysToKeepLogs.setText(String.valueOf(model.logger.MainLogger.getInstance().getMaxDaysToKeepLogs()));


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
                        .addComponent(jLabelDescription))
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
