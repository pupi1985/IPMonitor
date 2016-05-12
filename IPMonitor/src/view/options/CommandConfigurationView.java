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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;

import model.notification.configuration.CommandConfiguration;
import view.extras.JButtonInfo;
import view.panels.JPanelConfirmation;

public class CommandConfigurationView extends JDialog {

    private JButtonInfo jButtonInfo;
    private JPanelConfirmation jPanelConfirmation;
    private JTextArea jTextAreaCommand;

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
        JScrollPane jScrollPaneTextAreaCommand = new JScrollPane();
        jTextAreaCommand = new JTextArea();
        JLabel jLabelDescription = new JLabel("<html>Add the command to call in the first line.<br>"
                + "Add arguments to pass to the command in subsequent lines.</html>");
        jButtonInfo = new JButtonInfo();
        jPanelConfirmation = new JPanelConfirmation(true, true, false);

        setResizable(false);

        jPanelCenterBorder.setBorder(BorderFactory.createTitledBorder("Enter a command to execute"));

        jTextAreaCommand.setRows(10);
        jTextAreaCommand.setColumns(40);
        jTextAreaCommand.setText(CommandConfiguration.getInstance().getCommand());
        jScrollPaneTextAreaCommand.setViewportView(jTextAreaCommand);

        GroupLayout jPanelCenterBorderLayout = new GroupLayout(jPanelCenterBorder);
        jPanelCenterBorderLayout.setHorizontalGroup(
            jPanelCenterBorderLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(jPanelCenterBorderLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPaneTextAreaCommand)
                    .addContainerGap())
                .addGroup(jPanelCenterBorderLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabelDescription)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        jPanelCenterBorderLayout.setVerticalGroup(
            jPanelCenterBorderLayout.createParallelGroup(Alignment.TRAILING)
                .addGroup(jPanelCenterBorderLayout.createSequentialGroup()
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPaneTextAreaCommand)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanelCenterBorderLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(jLabelDescription)
                        .addComponent(jButtonInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        jPanelCenterBorder.setLayout(jPanelCenterBorderLayout);

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

    public JTextArea getJTextFieldFilePath() {
        return jTextAreaCommand;
    }

    public JButton getJButtonOk() {
        return jPanelConfirmation.getJButtonOk();
    }

    public JButton getJButtonCancel() {
        return jPanelConfirmation.getJButtonCancel();
    }
}
