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

package view.options.tests;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import view.panels.JPanelConfirmation;

public class OutputTestView extends JDialog {

    private JTextArea jTextAreaOutput;
    private JPanelConfirmation jPanelConfirmation;
    private JPanel jPanelCenterBorder;

    public OutputTestView(JDialog owner) {
        super(owner, true);
        initComponents();
        getRootPane().setDefaultButton(getJButtonOk());
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        JPanel jPanelCenter = new JPanel();
        jPanelCenterBorder = new JPanel();
        JScrollPane jScrollPaneTextAreaOutput = new JScrollPane();
        jScrollPaneTextAreaOutput.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneTextAreaOutput.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPaneTextAreaOutput.setAutoscrolls(true);
        jTextAreaOutput = new JTextArea();
        jTextAreaOutput.setEditable(false);
        jPanelConfirmation = new JPanelConfirmation(true, false, false);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPaneTextAreaOutput.setViewportView(jTextAreaOutput);
        jTextAreaOutput.setRows(20);
        jTextAreaOutput.setColumns(40);

        GroupLayout jPanelCenterBorderLayout = new GroupLayout(jPanelCenterBorder);
        jPanelCenterBorderLayout.setHorizontalGroup(
            jPanelCenterBorderLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(jPanelCenterBorderLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPaneTextAreaOutput)
                    .addContainerGap()));
        jPanelCenterBorderLayout.setVerticalGroup(
            jPanelCenterBorderLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(jPanelCenterBorderLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPaneTextAreaOutput)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
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

    public void setSubTitle(String subTitle) {
        jPanelCenterBorder.setBorder(BorderFactory.createTitledBorder(subTitle));
    }

    public JTextArea getJTextAreaOutput() {
        return jTextAreaOutput;
    }

    public JButton getJButtonOk() {
        return jPanelConfirmation.getJButtonOk();
    }
}
