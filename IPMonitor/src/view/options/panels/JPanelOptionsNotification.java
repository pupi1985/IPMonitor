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

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import model.ipmonitor.IPMonitor;
import model.notification.AudioNotification;
import model.notification.CommandNotification;
import model.notification.MailNotification;
import model.notification.VisualNotification;

public class JPanelOptionsNotification extends JPanel {
	
    private view.options.extras.JButtonConfigure jButtonAudioConfiguration;
    private view.extras.JButtonTest jButtonAudioTest;
    private view.options.extras.JButtonConfigure jButtonCommandConfiguration;
    private view.extras.JButtonTest jButtonCommandTest;
    private view.options.extras.JButtonConfigure jButtonMailConfiguration;
    private view.extras.JButtonTest jButtonMailTest;
    private view.options.extras.JButtonConfigure jButtonVisualConfiguration;
    private view.extras.JButtonTest jButtonVisualTest;
    private JCheckBox jCheckBoxEnableAudioNotification;
    private JCheckBox jCheckBoxEnableCommandNotification;
    private JCheckBox jCheckBoxEnableMailNotification;
    private JCheckBox jCheckBoxEnableVisualNotification;
    private JCheckBox jCheckBoxEnableVisualNotification1;
    
    private IPMonitor ipMonitor = null;

    public JPanelOptionsNotification() {
    }

    public JPanelOptionsNotification(IPMonitor ipMonitor) {
        this.ipMonitor = ipMonitor;
        initComponents();
    }

    private void initComponents() {
        jCheckBoxEnableAudioNotification = new JCheckBox();
        jCheckBoxEnableMailNotification = new JCheckBox();
        jCheckBoxEnableVisualNotification = new JCheckBox();
        jCheckBoxEnableCommandNotification = new JCheckBox();
        jCheckBoxEnableVisualNotification1 = new JCheckBox();
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

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxEnableCommandNotification)
                    .addComponent(jCheckBoxEnableVisualNotification)
                    .addComponent(jCheckBoxEnableMailNotification)
                    .addComponent(jCheckBoxEnableAudioNotification))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonCommandConfiguration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonCommandTest, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonVisualConfiguration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonVisualTest, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonMailConfiguration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonMailTest, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonAudioConfiguration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonAudioTest, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {jButtonAudioConfiguration, jButtonCommandConfiguration, jButtonMailConfiguration, jButtonVisualConfiguration});

        layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {jButtonAudioTest, jButtonCommandTest, jButtonMailTest, jButtonVisualTest});

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxEnableAudioNotification)
                    .addComponent(jButtonAudioConfiguration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAudioTest, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxEnableMailNotification)
                    .addComponent(jButtonMailConfiguration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonMailTest, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxEnableVisualNotification)
                    .addComponent(jButtonVisualConfiguration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonVisualTest, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxEnableCommandNotification)
                    .addComponent(jButtonCommandConfiguration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCommandTest, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

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
