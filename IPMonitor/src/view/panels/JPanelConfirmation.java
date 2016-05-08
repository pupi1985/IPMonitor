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

package view.panels;

import java.awt.Component;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

public class JPanelConfirmation extends JPanel {

    private JButton jButtonApply;
    private JButton jButtonCancel;
    private JButton jButtonOk;

    public JPanelConfirmation(boolean okVisible, boolean cancelVisible, boolean applyVisible) {
        initComponents();
        jButtonOk.setVisible(okVisible);
        jButtonCancel.setVisible(cancelVisible);
        jButtonApply.setVisible(applyVisible);
    }

    private void initComponents() {

        JPanel jPanelTemp = new JPanel();
        jButtonOk = new JButton();
        jButtonCancel = new JButton();
        jButtonApply = new JButton();

        jButtonOk.setText("Ok");

        jButtonCancel.setText("Cancel");

        jButtonApply.setText("Apply");

        GroupLayout jPanelTempLayout = new GroupLayout(jPanelTemp);
        jPanelTemp.setLayout(jPanelTempLayout);
        jPanelTempLayout.setHorizontalGroup(
            jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTempLayout.createSequentialGroup()
                .addComponent(jButtonOk)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonCancel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonApply))
        );

        jPanelTempLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {jButtonApply, jButtonCancel, jButtonOk});

        jPanelTempLayout.setVerticalGroup(
            jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jButtonOk)
            .addGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(jButtonCancel)
                .addComponent(jButtonApply))
        );

        jPanelTempLayout.linkSize(SwingConstants.VERTICAL, new Component[] {jButtonApply, jButtonCancel, jButtonOk});

        add(jPanelTemp);
    }

    public JButton getJButtonApply() {
        return jButtonApply;
    }

    public JButton getJButtonCancel() {
        return jButtonCancel;
    }

    public JButton getJButtonOk() {
        return jButtonOk;
    }
}
