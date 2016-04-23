/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package view;

import model.extras.AboutInformation;
import controller.extras.*;
import java.awt.*;
import javax.swing.*;
import model.ipmonitor.*;

public class MainView extends JFrame {

    public MainView(IPMonitor ipMonitor) {
        this.ipMonitor = ipMonitor;
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu = new java.awt.PopupMenu();
        menuItemCheckIP = new java.awt.MenuItem();
        menuItemStartStop = new java.awt.MenuItem();
        menuItemOptions = new java.awt.MenuItem();
        menuItemExit = new java.awt.MenuItem();
        javax.swing.JPanel jPanelCenter = new javax.swing.JPanel();
        javax.swing.JPanel jPanelCenterBorder = new javax.swing.JPanel();
        javax.swing.JPanel jPanelMainPanel = new javax.swing.JPanel();
        javax.swing.JLabel jLabelStatus = new javax.swing.JLabel();
        javax.swing.JLabel jLabelInterval = new javax.swing.JLabel();
        javax.swing.JLabel jLabelCurrentIP = new javax.swing.JLabel();
        javax.swing.JLabel jLabelLastChecked = new javax.swing.JLabel();
        javax.swing.JLabel jLabelLastChange = new javax.swing.JLabel();
        jLabelStatusField = new javax.swing.JLabel();
        jLabelIntervalField = new javax.swing.JLabel();
        jLabelCurrentIPField = new javax.swing.JLabel();
        jLabelLastCheckedField = new javax.swing.JLabel();
        jLabelLastChangeField = new javax.swing.JLabel();
        javax.swing.JPanel jPanelSouth = new javax.swing.JPanel();
        javax.swing.JPanel jPanelSouthTemp = new javax.swing.JPanel();
        jButtonStartStop = new javax.swing.JButton();
        jButtonCheckIP = new javax.swing.JButton();
        jMenuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu jMenuFile = new javax.swing.JMenu();
        jMenuItemFileOptions = new javax.swing.JMenuItem();
        javax.swing.JSeparator jSeparatorFile1 = new javax.swing.JSeparator();
        jMenuItemFileExit = new javax.swing.JMenuItem();
        javax.swing.JMenu jMenuHelp = new javax.swing.JMenu();
        jMenuItemHelpAbout = new javax.swing.JMenuItem();

        popupMenu.setLabel("PopupMenu");

        menuItemCheckIP.setFont(menuItemCheckIP.getFont());
        menuItemCheckIP.setLabel("Check IP"); // NOI18N
        popupMenu.add(menuItemCheckIP);

        menuItemStartStop.setFont(menuItemStartStop.getFont());
        menuItemStartStop.setLabel("Start");
        popupMenu.add(menuItemStartStop);
        popupMenu.addSeparator();
        menuItemOptions.setFont(menuItemOptions.getFont());
        menuItemOptions.setLabel("Options...");
        popupMenu.add(menuItemOptions);
        popupMenu.addSeparator();
        menuItemExit.setFont(menuItemExit.getFont());
        menuItemExit.setLabel("Exit");
        popupMenu.add(menuItemExit);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(AboutInformation.getInstance().getName());
        setIconImage(AboutInformation.getInstance().getImage());

        jPanelCenterBorder.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelCenterBorder.setLayout(new java.awt.GridBagLayout());

        jLabelStatus.setText("Status:");

        jLabelInterval.setText("Interval:");

        jLabelCurrentIP.setText("Current IP:");

        jLabelLastChecked.setText("Last checked:");

        jLabelLastChange.setText("Last change:");

        jLabelStatusField.setForeground(new java.awt.Color(192, 0, 0));
        jLabelStatusField.setText(ipMonitor.isChecking() ? "On" : "Off");

        jLabelIntervalField.setText(new TimeUnitConverter(ipMonitor.getInterval()).toString());

        jLabelCurrentIPField.setText("Not obtained");

        javax.swing.GroupLayout jPanelMainPanelLayout = new javax.swing.GroupLayout(jPanelMainPanel);
        jPanelMainPanel.setLayout(jPanelMainPanelLayout);
        jPanelMainPanelLayout.setHorizontalGroup(
            jPanelMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelStatus)
                    .addComponent(jLabelInterval)
                    .addComponent(jLabelCurrentIP)
                    .addComponent(jLabelLastChecked)
                    .addComponent(jLabelLastChange))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelStatusField)
                    .addComponent(jLabelIntervalField)
                    .addComponent(jLabelCurrentIPField)
                    .addComponent(jLabelLastCheckedField)
                    .addComponent(jLabelLastChangeField))
                .addContainerGap())
        );
        jPanelMainPanelLayout.setVerticalGroup(
            jPanelMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMainPanelLayout.createSequentialGroup()
                        .addComponent(jLabelStatusField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelIntervalField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelCurrentIPField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelLastCheckedField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelLastChangeField))
                    .addGroup(jPanelMainPanelLayout.createSequentialGroup()
                        .addComponent(jLabelStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelInterval)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelCurrentIP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelLastChecked)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelLastChange)))
                .addContainerGap())
        );

        jPanelCenterBorder.add(jPanelMainPanel, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout jPanelCenterLayout = new javax.swing.GroupLayout(jPanelCenter);
        jPanelCenter.setLayout(jPanelCenterLayout);
        jPanelCenterLayout.setHorizontalGroup(
            jPanelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCenterBorder, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelCenterLayout.setVerticalGroup(
            jPanelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCenterBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanelCenter, java.awt.BorderLayout.CENTER);

        jButtonStartStop.setText("Start");

        jButtonCheckIP.setText("Check IP");

        javax.swing.GroupLayout jPanelSouthTempLayout = new javax.swing.GroupLayout(jPanelSouthTemp);
        jPanelSouthTemp.setLayout(jPanelSouthTempLayout);
        jPanelSouthTempLayout.setHorizontalGroup(
            jPanelSouthTempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSouthTempLayout.createSequentialGroup()
                .addComponent(jButtonStartStop)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonCheckIP))
        );

        jPanelSouthTempLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonCheckIP, jButtonStartStop});

        jPanelSouthTempLayout.setVerticalGroup(
            jPanelSouthTempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSouthTempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButtonStartStop)
                .addComponent(jButtonCheckIP))
        );

        jPanelSouthTempLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonCheckIP, jButtonStartStop});

        jPanelSouth.add(jPanelSouthTemp);

        getContentPane().add(jPanelSouth, java.awt.BorderLayout.SOUTH);

        jMenuFile.setText("File");
        jMenuFile.setMnemonic(KeyStroke.getKeyStroke(jMenuFile.getText().substring(0, 1).toUpperCase()).getKeyCode());

        jMenuItemFileOptions.setText("Options...");
        jMenuFile.add(jMenuItemFileOptions);
        jMenuFile.add(jSeparatorFile1);

        jMenuItemFileExit.setText("Exit");
        jMenuFile.add(jMenuItemFileExit);

        jMenuBar.add(jMenuFile);

        jMenuHelp.setText("Help"); // NOI18N
        jMenuHelp.setMnemonic(KeyStroke.getKeyStroke(jMenuHelp.getText().substring(0, 1).toUpperCase()).getKeyCode());
        jMenuHelp.add(jMenuItemHelpAbout);

        jMenuBar.add(jMenuHelp);

        setJMenuBar(jMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCheckIP;
    private javax.swing.JButton jButtonStartStop;
    private javax.swing.JLabel jLabelCurrentIPField;
    private javax.swing.JLabel jLabelIntervalField;
    private javax.swing.JLabel jLabelLastChangeField;
    private javax.swing.JLabel jLabelLastCheckedField;
    private javax.swing.JLabel jLabelStatusField;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem jMenuItemFileExit;
    private javax.swing.JMenuItem jMenuItemFileOptions;
    private javax.swing.JMenuItem jMenuItemHelpAbout;
    private java.awt.MenuItem menuItemCheckIP;
    private java.awt.MenuItem menuItemExit;
    private java.awt.MenuItem menuItemOptions;
    private java.awt.MenuItem menuItemStartStop;
    private java.awt.PopupMenu popupMenu;
    // End of variables declaration//GEN-END:variables
    private IPMonitor ipMonitor = null;

    public JButton getJButtonCheckIP() {
        return jButtonCheckIP;
    }

    public JButton getJButtonStartStop() {
        return jButtonStartStop;
    }

    public JLabel getJLabelCurrentIPField() {
        return jLabelCurrentIPField;
    }

    public JLabel getJLabelIntervalField() {
        return jLabelIntervalField;
    }

    public JLabel getJLabelLastChangeField() {
        return jLabelLastChangeField;
    }

    public JLabel getJLabelLastCheckedField() {
        return jLabelLastCheckedField;
    }

    public JLabel getJLabelStatusField() {
        return jLabelStatusField;
    }

    public PopupMenu getPopupMenu() {
        return popupMenu;
    }

    public MenuItem getMenuItemStartStop() {
        return menuItemStartStop;
    }

    public MenuItem getMenuItemCheckIP() {
        return menuItemCheckIP;
    }

    public MenuItem getMenuItemOptions() {
        return menuItemOptions;
    }

    public MenuItem getMenuItemExit() {
        return menuItemExit;
    }

    public JMenuItem getJMenuItemFileOptions() {
        return jMenuItemFileOptions;
    }
    
    public JMenuItem getJMenuItemFileExit() {
        return jMenuItemFileExit;
    }

    public JMenuItem getJMenuItemHelpAbout() {
        return jMenuItemHelpAbout;
    }
}
