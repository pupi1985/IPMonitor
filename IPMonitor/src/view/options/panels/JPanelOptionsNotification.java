/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package view.options.panels;

import javax.swing.*;
import model.ipmonitor.*;
import model.notification.*;

public class JPanelOptionsNotification extends JPanel {

    public JPanelOptionsNotification() {
    }

    public JPanelOptionsNotification(IPMonitor ipMonitor) {
        this.ipMonitor = ipMonitor;
        initComponents();
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxEnableAudioNotification = new javax.swing.JCheckBox();
        jCheckBoxEnableMailNotification = new javax.swing.JCheckBox();
        jCheckBoxEnableVisualNotification = new javax.swing.JCheckBox();
        jCheckBoxEnableCommandNotification = new javax.swing.JCheckBox();
        jCheckBoxEnableVisualNotification1 = new javax.swing.JCheckBox();
        jButtonAudioConfiguration = new view.options.extras.JButtonConfigure();
        jButtonMailConfiguration = new view.options.extras.JButtonConfigure();
        jButtonVisualConfiguration = new view.options.extras.JButtonConfigure();
        jButtonCommandConfiguration = new view.options.extras.JButtonConfigure();
        jButtonAudioTest = new view.extras.JButtonTest();
        jButtonMailTest = new view.extras.JButtonTest();
        jButtonVisualTest = new view.extras.JButtonTest();
        jButtonCommandTest = new view.extras.JButtonTest();

        jCheckBoxEnableAudioNotification.setSelected(ipMonitor.hasNotification(AudioNotification.getInstance()));
        jCheckBoxEnableAudioNotification.setText("Enable audio notification");

        jCheckBoxEnableMailNotification.setSelected(ipMonitor.hasNotification(MailNotification.getInstance()));
        jCheckBoxEnableMailNotification.setText("Enable mail notification");

        jCheckBoxEnableVisualNotification.setSelected(ipMonitor.hasNotification(VisualNotification.getInstance()));
        jCheckBoxEnableVisualNotification.setText("Enable visual notification");

        jCheckBoxEnableCommandNotification.setSelected(ipMonitor.hasNotification(CommandNotification.getInstance()));
        jCheckBoxEnableCommandNotification.setText("Enable command notification");

        jCheckBoxEnableVisualNotification1.setSelected(ipMonitor.hasNotification(VisualNotification.getInstance()));
        jCheckBoxEnableVisualNotification1.setText("Enable visual notification");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxEnableCommandNotification)
                    .addComponent(jCheckBoxEnableVisualNotification)
                    .addComponent(jCheckBoxEnableMailNotification)
                    .addComponent(jCheckBoxEnableAudioNotification))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonCommandConfiguration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonCommandTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonVisualConfiguration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonVisualTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonMailConfiguration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonMailTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonAudioConfiguration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonAudioTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonAudioConfiguration, jButtonCommandConfiguration, jButtonMailConfiguration, jButtonVisualConfiguration});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonAudioTest, jButtonCommandTest, jButtonMailTest, jButtonVisualTest});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxEnableAudioNotification)
                    .addComponent(jButtonAudioConfiguration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAudioTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxEnableMailNotification)
                    .addComponent(jButtonMailConfiguration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonMailTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxEnableVisualNotification)
                    .addComponent(jButtonVisualConfiguration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonVisualTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxEnableCommandNotification)
                    .addComponent(jButtonCommandConfiguration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCommandTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.options.extras.JButtonConfigure jButtonAudioConfiguration;
    private view.extras.JButtonTest jButtonAudioTest;
    private view.options.extras.JButtonConfigure jButtonCommandConfiguration;
    private view.extras.JButtonTest jButtonCommandTest;
    private view.options.extras.JButtonConfigure jButtonMailConfiguration;
    private view.extras.JButtonTest jButtonMailTest;
    private view.options.extras.JButtonConfigure jButtonVisualConfiguration;
    private view.extras.JButtonTest jButtonVisualTest;
    private javax.swing.JCheckBox jCheckBoxEnableAudioNotification;
    private javax.swing.JCheckBox jCheckBoxEnableCommandNotification;
    private javax.swing.JCheckBox jCheckBoxEnableMailNotification;
    private javax.swing.JCheckBox jCheckBoxEnableVisualNotification;
    private javax.swing.JCheckBox jCheckBoxEnableVisualNotification1;
    // End of variables declaration//GEN-END:variables
    private IPMonitor ipMonitor = null;

    public JButton getJButtonAudioConfiguration() {
        return jButtonAudioConfiguration;
    }

    public JButton getJButtonAudioTest() {
        return jButtonAudioTest;
    }

    public JButton getJButtonCommandConfiguration() {
        return jButtonCommandConfiguration;
    }

    public JButton getJButtonCommandTest() {
        return jButtonCommandTest;
    }

    public JButton getJButtonMailConfiguration() {
        return jButtonMailConfiguration;
    }

    public JButton getJButtonMailTest() {
        return jButtonMailTest;
    }

    public JButton getJButtonVisualConfiguration() {
        return jButtonVisualConfiguration;
    }

    public JButton getJButtonVisualTest() {
        return jButtonVisualTest;
    }

    public JCheckBox getJCheckBoxEnableAudioNotification() {
        return jCheckBoxEnableAudioNotification;
    }

    public JCheckBox getJCheckBoxEnableCommandNotification() {
        return jCheckBoxEnableCommandNotification;
    }

    public JCheckBox getJCheckBoxEnableMailNotification() {
        return jCheckBoxEnableMailNotification;
    }

    public JCheckBox getJCheckBoxEnableVisualNotification() {
        return jCheckBoxEnableVisualNotification;
    }
}
