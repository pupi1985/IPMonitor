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

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Locale;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import model.service.ServiceManager;

public class JPanelOptionsService extends JPanel {

    private JButton jButtonInstall;
    private JButton jButtonStart;
    private JButton jButtonStop;
    private JButton jButtonTest;
    private JButton jButtonUninstall;
    private JPanel jPanelTemp;

    public JPanelOptionsService() {
        initComponents();
    }

    private void initComponents() {
        jPanelTemp = new JPanel();
        jButtonInstall = new JButton();
        jButtonStart = new JButton();
        jButtonTest = new JButton();
        jButtonUninstall = new JButton();
        jButtonStop = new JButton();

        setLayout(new GridBagLayout());

        String serviceName = ServiceManager.getInstance().getService().getServiceName().toLowerCase(Locale.ENGLISH);

        jButtonInstall.setText("Install " + serviceName);

        jButtonStart.setText("Start " + serviceName);

        jButtonTest.setText("Test " + serviceName);

        jButtonUninstall.setText("Uninstall " + serviceName);

        jButtonStop.setText("Stop " + serviceName);

        GroupLayout jPanelTempLayout = new GroupLayout(jPanelTemp);
        jPanelTemp.setLayout(jPanelTempLayout);
        jPanelTempLayout.setHorizontalGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addGroup(jPanelTempLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonInstall, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonUninstall, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanelTempLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonStart, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonStop, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanelTempLayout.createSequentialGroup()
                .addComponent(jButtonTest, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        jPanelTempLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {
                jButtonInstall, jButtonStart, jButtonStop, jButtonTest, jButtonUninstall
        });

        jPanelTempLayout.setVerticalGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTempLayout.createSequentialGroup()
                .addContainerGap()
            .addGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(jButtonInstall)
                .addComponent(jButtonUninstall))
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(jButtonStart)
                .addComponent(jButtonStop))
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(jButtonTest))
            .addContainerGap()));

        jPanelTempLayout.linkSize(SwingConstants.VERTICAL, new Component[] {
                jButtonInstall, jButtonStart, jButtonStop, jButtonTest, jButtonUninstall
        });

        add(jPanelTemp, new GridBagConstraints());
    }

    public JButton getJButtonInstall() {
        return jButtonInstall;
    }

    public JButton getJButtonUninstall() {
        return jButtonUninstall;
    }

    public JButton getJButtonStart() {
        return jButtonStart;
    }

    public JButton getJButtonStop() {
        return jButtonStop;
    }

    public JButton getJButtonTest() {
        return jButtonTest;
    }

    public JPanel getJPanelInnerPanel() {
        return jPanelTemp;
    }
}
