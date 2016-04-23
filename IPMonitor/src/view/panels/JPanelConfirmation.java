/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package view.panels;

import javax.swing.*;

public class JPanelConfirmation extends javax.swing.JPanel {

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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel jPanelTemp = new javax.swing.JPanel();
        jButtonOk = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jButtonApply = new javax.swing.JButton();

        jButtonOk.setText("Ok");

        jButtonCancel.setText("Cancel"); // NOI18N

        jButtonApply.setText("Apply");

        javax.swing.GroupLayout jPanelTempLayout = new javax.swing.GroupLayout(jPanelTemp);
        jPanelTemp.setLayout(jPanelTempLayout);
        jPanelTempLayout.setHorizontalGroup(
            jPanelTempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTempLayout.createSequentialGroup()
                .addComponent(jButtonOk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonApply))
        );

        jPanelTempLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonApply, jButtonCancel, jButtonOk});

        jPanelTempLayout.setVerticalGroup(
            jPanelTempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtonOk)
            .addGroup(jPanelTempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButtonCancel)
                .addComponent(jButtonApply))
        );

        jPanelTempLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonApply, jButtonCancel, jButtonOk});

        add(jPanelTemp);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonApply;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOk;
    // End of variables declaration//GEN-END:variables
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
