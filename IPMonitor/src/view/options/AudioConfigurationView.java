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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import model.notification.configuration.AudioConfiguration;
import view.panels.JPanelConfirmation;

public class AudioConfigurationView extends JDialog {
	
	private JButton jButtonBrowse;
	private JPanelConfirmation jPanelConfirmation;
	private JTextField jTextFieldFilePath;

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
        JPanel jPanelCenter = new JPanel();
        JPanel jPanelCenterBorder = new JPanel();
        jTextFieldFilePath = new JTextField();
        jButtonBrowse = new JButton();
        jPanelConfirmation = new JPanelConfirmation(false);

        setResizable(false);

        jPanelCenterBorder.setBorder(BorderFactory.createTitledBorder("Select a file to play"));

        jTextFieldFilePath.setText(AudioConfiguration.getInstance().getFileName());

        jButtonBrowse.setText("Browse...");

        GroupLayout jPanelCenterBorderLayout = new GroupLayout(jPanelCenterBorder);
        jPanelCenterBorder.setLayout(jPanelCenterBorderLayout);
        jPanelCenterBorderLayout.setHorizontalGroup(
            jPanelCenterBorderLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanelCenterBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldFilePath, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBrowse)
                .addContainerGap())
        );
        jPanelCenterBorderLayout.setVerticalGroup(
            jPanelCenterBorderLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCenterBorderLayout.createSequentialGroup()
                .addGroup(jPanelCenterBorderLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBrowse)
                    .addComponent(jTextFieldFilePath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
