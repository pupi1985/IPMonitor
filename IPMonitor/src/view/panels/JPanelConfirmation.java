/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
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

    public JPanelConfirmation(boolean cancelVisible, boolean applyVisible) {
        this(true, cancelVisible, applyVisible);
    }

    public JPanelConfirmation(boolean applyVisible) {
        this(true, true, applyVisible);
    }

    public JPanelConfirmation() {
        this(true, false, false);
    }

    private void initComponents() {

        JPanel jPanelTemp = new JPanel();
        jButtonOk = new JButton();
        jButtonCancel = new JButton();
        jButtonApply = new JButton();

        jButtonOk.setText("Ok");

        jButtonCancel.setText("Cancel"); // NOI18N

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
