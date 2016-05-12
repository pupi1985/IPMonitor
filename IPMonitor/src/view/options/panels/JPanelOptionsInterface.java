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

import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;

import controller.extras.LookAndFeelInfoModel;
import controller.extras.LookAndFeelInfoWrapper;
import model.configuration.ConfigurationManager;

public class JPanelOptionsInterface extends JPanel {

    private JList<LookAndFeelInfoWrapper> jListLookAndFeel;

    public JPanelOptionsInterface() {
        initComponents();
    }

    private void initComponents() {
        JLabel jLabelLookAndFeel = new JLabel();
        JScrollPane jScrollPaneLookAndFeel = new JScrollPane();
        jListLookAndFeel = new JList<LookAndFeelInfoWrapper>();

        setMinimumSize(new Dimension(200, 150));

        jLabelLookAndFeel.setText("Select a look and feel:");

        LookAndFeelInfoModel lookAndFeelInfoModel = new LookAndFeelInfoModel();
        jListLookAndFeel.setModel(lookAndFeelInfoModel);
        jListLookAndFeel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jListLookAndFeel.setVisibleRowCount(6);
        jScrollPaneLookAndFeel.setViewportView(jListLookAndFeel);
        jListLookAndFeel.setSelectedValue(
                lookAndFeelInfoModel.getLookAndFeelInfoWrapper(
                        ConfigurationManager.getInstance().getVisualConfigurationManager().getLookAndFeelClassName()),
                true);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(jScrollPaneLookAndFeel, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(jLabelLookAndFeel))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabelLookAndFeel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneLookAndFeel, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addContainerGap())
        );
    }

    public JList<LookAndFeelInfoWrapper> getJListLookAndFeel() {
        return jListLookAndFeel;
    }
}
