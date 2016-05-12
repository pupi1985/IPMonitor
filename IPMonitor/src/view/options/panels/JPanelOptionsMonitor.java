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

import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import controller.extras.TimeUnitConverter;
import model.configuration.ConfigurationManager;
import model.ipmonitor.IPMonitor;

public class JPanelOptionsMonitor extends JPanel {

    private JCheckBox jCheckBoxAutostart;
    private JTextField jTextFieldHours;
    private JTextField jTextFieldMinutes;
    private JTextField jTextFieldSeconds;
    private JTextField jTextFieldURL;

    private IPMonitor ipMonitor = null;

    public JPanelOptionsMonitor() {
    }

    public JPanelOptionsMonitor(IPMonitor ipMonitor) {
        this.ipMonitor = ipMonitor;
        initComponents();
    }

    private void initComponents() {
        JLabel jLabelInterval = new JLabel();
        JLabel jLabelCheckingURL = new JLabel();
        TimeUnitConverter timeUnitConverter = new TimeUnitConverter(ipMonitor.getInterval());
        jTextFieldHours = new JTextField();
        JLabel jLabelColon1 = new JLabel();
        JLabel jLabelColon2 = new JLabel();
        jTextFieldMinutes = new JTextField();
        jTextFieldSeconds = new JTextField();
        jTextFieldURL = new JTextField();
        JLabel jLabelHMS = new JLabel();
        jCheckBoxAutostart = new JCheckBox();

        jLabelInterval.setText("Interval:");

        jLabelCheckingURL.setText("Checking URL:");

        jTextFieldHours.setColumns(3);
        jTextFieldHours.setHorizontalAlignment(JTextField.CENTER);
        jTextFieldHours.setText(String.valueOf(timeUnitConverter.getHours()));

        jLabelColon1.setText(":");

        jLabelColon2.setText(":");

        jTextFieldMinutes.setColumns(3);
        jTextFieldMinutes.setHorizontalAlignment(JTextField.CENTER);
        jTextFieldMinutes.setText(String.valueOf(timeUnitConverter.getMinutes()));

        jTextFieldSeconds.setColumns(3);
        jTextFieldSeconds.setHorizontalAlignment(JTextField.CENTER);
        jTextFieldSeconds.setText(String.valueOf(timeUnitConverter.getSeconds()));

        jTextFieldURL.setText(ipMonitor.getUrl());

        jLabelHMS.setText("(H:M:S)");

        jCheckBoxAutostart.setSelected(ConfigurationManager.getInstance().isAutostart());
        jCheckBoxAutostart.setText("Autostart when IP Monitor launches");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelCheckingURL)
                            .addComponent(jLabelInterval))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldHours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelColon1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldMinutes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelColon2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldSeconds, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelHMS))
                            .addComponent(jTextFieldURL, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)))
                    .addComponent(jCheckBoxAutostart, GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldHours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelColon1)
                    .addComponent(jTextFieldMinutes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelColon2)
                    .addComponent(jTextFieldSeconds, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelHMS)
                    .addComponent(jLabelInterval))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCheckingURL)
                    .addComponent(jTextFieldURL, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxAutostart)
                .addContainerGap())
        );
    }

    public JTextField getJTextFieldHours() {
        return jTextFieldHours;
    }

    public JTextField getJTextFieldMinutes() {
        return jTextFieldMinutes;
    }

    public JTextField getJTextFieldSeconds() {
        return jTextFieldSeconds;
    }

    public JTextField getJTextFieldURL() {
        return jTextFieldURL;
    }

    public JCheckBox getJCheckBoxAutoStart() {
        return jCheckBoxAutostart;
    }
}
