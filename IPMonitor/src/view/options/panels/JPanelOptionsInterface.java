/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
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
        jListLookAndFeel.setSelectedValue(lookAndFeelInfoModel.getLookAndFeelInfoWrapper(ConfigurationManager.getInstance().getVisualConfigurationManager().getLookAndFeelClassName()), true);

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
