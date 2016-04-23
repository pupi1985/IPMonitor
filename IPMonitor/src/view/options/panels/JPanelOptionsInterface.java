/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package view.options.panels;

import javax.swing.JList;
import javax.swing.JPanel;

import controller.extras.LookAndFeelInfoModel;
import model.configuration.ConfigurationManager;

public class JPanelOptionsInterface extends JPanel {
	
    private javax.swing.JList jListLookAndFeel;

    public JPanelOptionsInterface() {
        initComponents();
    }

    private void initComponents() {
        javax.swing.JLabel jLabelLookAndFeel = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPaneLookAndFeel = new javax.swing.JScrollPane();
        jListLookAndFeel = new javax.swing.JList();

        setMinimumSize(new java.awt.Dimension(200, 150));

        jLabelLookAndFeel.setText("Select a look and feel:");

        LookAndFeelInfoModel lookAndFeelInfoModel = new LookAndFeelInfoModel();
        jListLookAndFeel.setModel(lookAndFeelInfoModel);
        jListLookAndFeel.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListLookAndFeel.setVisibleRowCount(6);
        jScrollPaneLookAndFeel.setViewportView(jListLookAndFeel);
        jListLookAndFeel.setSelectedValue(lookAndFeelInfoModel.getLookAndFeelInfoWrapper(ConfigurationManager.getInstance().getVisualConfigurationManager().getLookAndFeelClassName()), true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jScrollPaneLookAndFeel, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(jLabelLookAndFeel))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabelLookAndFeel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneLookAndFeel, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addContainerGap())
        );
    }

    public JList getJListLookAndFeel() {
        return jListLookAndFeel;
    }
}
