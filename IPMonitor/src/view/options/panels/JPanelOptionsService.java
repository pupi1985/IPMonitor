/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package view.options.panels;

import controller.extras.*;
import javax.swing.*;
import model.configuration.*;
import model.service.exceptions.*;
import model.service.os.*;
import model.service.services.*;

public class JPanelOptionsService extends javax.swing.JPanel {

    private String serviceName;

    public JPanelOptionsService() {
        try {
            serviceName = ServiceManager.getInstance().getService().getServiceName().toLowerCase();
        } catch (OSNotSupportedException e) {
            serviceName = new Windows().getService().getServiceName().toLowerCase();
        }
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelTemp = new javax.swing.JPanel();
        OSModel OSModel = new OSModel();
        jComboBoxOS = new JComboBox(OSModel);
        //jComboBoxOS.getModel().setSelectedItem(jComboBoxOS.getModel().getElementAt(0));
        if (jComboBoxOS.getModel().getSize() == 1) {
            jComboBoxOS.setEnabled(false);
        }
        jButtonInstall = new javax.swing.JButton();
        jButtonStart = new javax.swing.JButton();
        jButtonTest = new javax.swing.JButton();
        jButtonUninstall = new javax.swing.JButton();
        jButtonStop = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jButtonInstall.setText("Install " + serviceName);

        jButtonStart.setText("Start " + serviceName);

        jButtonTest.setText("Test " + serviceName);

        jButtonUninstall.setText("Uninstall " + serviceName);

        jButtonStop.setText("Stop " + serviceName);

        javax.swing.GroupLayout jPanelTempLayout = new javax.swing.GroupLayout(jPanelTemp);
        jPanelTemp.setLayout(jPanelTempLayout);
        jPanelTempLayout.setHorizontalGroup(
            jPanelTempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTempLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButtonStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonInstall, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonStop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonTest, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonUninstall, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanelTempLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonInstall, jButtonStart, jButtonStop, jButtonTest, jButtonUninstall, jComboBoxOS});

        jPanelTempLayout.setVerticalGroup(
            jPanelTempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTempLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonTest))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonInstall)
                    .addComponent(jButtonUninstall))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonStart)
                    .addComponent(jButtonStop))
                .addContainerGap())
        );

        jPanelTempLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonInstall, jButtonStart, jButtonStop, jButtonTest, jButtonUninstall, jComboBoxOS});

        try {
            jComboBoxOS.getModel().setSelectedItem((OSManager.getInstance().getClassForID(ConfigurationManager.getInstance().getOsId())).newInstance());
        } catch (Exception e) {
            jComboBoxOS.setSelectedIndex(0);
        }

        add(jPanelTemp, new java.awt.GridBagConstraints());
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonInstall;
    private javax.swing.JButton jButtonStart;
    private javax.swing.JButton jButtonStop;
    private javax.swing.JButton jButtonTest;
    private javax.swing.JButton jButtonUninstall;
    private javax.swing.JComboBox jComboBoxOS;
    private javax.swing.JPanel jPanelTemp;
    // End of variables declaration//GEN-END:variables

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

    public JComboBox getJComboBoxOS() {
        return jComboBoxOS;
    }

    public JPanel getJPanelInnerPanel() {
        return jPanelTemp;
    }
}
