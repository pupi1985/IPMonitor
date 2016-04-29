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

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.MenuItem;
import java.awt.PopupMenu;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import controller.extras.TimeUnitConverter;
import model.extras.AboutInformation;
import model.ipmonitor.IPMonitor;

public class MainView extends JFrame {

    private JButton jButtonCheckIP;
    private JButton jButtonStartStop;
    private JLabel jLabelCurrentIPField;
    private JLabel jLabelIntervalField;
    private JLabel jLabelLastChangeField;
    private JLabel jLabelLastCheckedField;
    private JLabel jLabelStatusField;
    private JMenuBar jMenuBar;
    private JMenuItem jMenuItemFileExit;
    private JMenuItem jMenuItemFileOptions;
    private JMenuItem jMenuItemHelpAbout;
    private MenuItem menuItemCheckIP;
    private MenuItem menuItemExit;
    private MenuItem menuItemOptions;
    private MenuItem menuItemStartStop;
    private PopupMenu popupMenu;

    private IPMonitor ipMonitor = null;

    public MainView(IPMonitor ipMonitor) {
        this.ipMonitor = ipMonitor;
        initComponents();
    }

    private void initComponents() {
        popupMenu = new PopupMenu();
        menuItemCheckIP = new MenuItem();
        menuItemStartStop = new MenuItem();
        menuItemOptions = new MenuItem();
        menuItemExit = new MenuItem();
        JPanel jPanelCenter = new JPanel();
        JPanel jPanelCenterBorder = new JPanel();
        JPanel jPanelMainPanel = new JPanel();
        JLabel jLabelStatus = new JLabel();
        JLabel jLabelInterval = new JLabel();
        JLabel jLabelCurrentIP = new JLabel();
        JLabel jLabelLastChecked = new JLabel();
        JLabel jLabelLastChange = new JLabel();
        jLabelStatusField = new JLabel();
        jLabelIntervalField = new JLabel();
        jLabelCurrentIPField = new JLabel();
        jLabelLastCheckedField = new JLabel();
        jLabelLastChangeField = new JLabel();
        JPanel jPanelSouth = new JPanel();
        JPanel jPanelSouthTemp = new JPanel();
        jButtonStartStop = new JButton();
        jButtonCheckIP = new JButton();
        jMenuBar = new JMenuBar();
        JMenu jMenuFile = new JMenu();
        jMenuItemFileOptions = new JMenuItem();
        JSeparator jSeparatorFile1 = new JSeparator();
        jMenuItemFileExit = new JMenuItem();
        JMenu jMenuHelp = new JMenu();
        jMenuItemHelpAbout = new JMenuItem();

        popupMenu.setLabel("PopupMenu");

        menuItemCheckIP.setFont(menuItemCheckIP.getFont());
        menuItemCheckIP.setLabel("Check IP");
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

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(AboutInformation.getInstance().getName());
        setIconImage(AboutInformation.getInstance().getImage());

        jPanelCenterBorder.setBorder(BorderFactory.createEtchedBorder());
        jPanelCenterBorder.setLayout(new GridBagLayout());

        jLabelStatus.setText("Status:");

        jLabelInterval.setText("Interval:");

        jLabelCurrentIP.setText("Current IP:");

        jLabelLastChecked.setText("Last checked:");

        jLabelLastChange.setText("Last change:");

        jLabelStatusField.setForeground(new Color(192, 0, 0));
        jLabelStatusField.setText(ipMonitor.isChecking() ? "On" : "Off");

        jLabelIntervalField.setText(new TimeUnitConverter(ipMonitor.getInterval()).toString());

        jLabelCurrentIPField.setText("Not obtained");

        GroupLayout jPanelMainPanelLayout = new GroupLayout(jPanelMainPanel);
        jPanelMainPanel.setLayout(jPanelMainPanelLayout);
        jPanelMainPanelLayout.setHorizontalGroup(
            jPanelMainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelStatus)
                    .addComponent(jLabelInterval)
                    .addComponent(jLabelCurrentIP)
                    .addComponent(jLabelLastChecked)
                    .addComponent(jLabelLastChange))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelStatusField)
                    .addComponent(jLabelIntervalField)
                    .addComponent(jLabelCurrentIPField)
                    .addComponent(jLabelLastCheckedField)
                    .addComponent(jLabelLastChangeField))
                .addContainerGap())
        );
        jPanelMainPanelLayout.setVerticalGroup(
            jPanelMainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMainPanelLayout.createSequentialGroup()
                        .addComponent(jLabelStatusField)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelIntervalField)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelCurrentIPField)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelLastCheckedField)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelLastChangeField))
                    .addGroup(jPanelMainPanelLayout.createSequentialGroup()
                        .addComponent(jLabelStatus)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelInterval)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelCurrentIP)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelLastChecked)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelLastChange)))
                .addContainerGap())
        );

        jPanelCenterBorder.add(jPanelMainPanel, new GridBagConstraints());

        GroupLayout jPanelCenterLayout = new GroupLayout(jPanelCenter);
        jPanelCenter.setLayout(jPanelCenterLayout);
        jPanelCenterLayout.setHorizontalGroup(
            jPanelCenterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCenterBorder, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelCenterLayout.setVerticalGroup(
            jPanelCenterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCenterBorder, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanelCenter, BorderLayout.CENTER);

        jButtonStartStop.setText("Start");

        jButtonCheckIP.setText("Check IP");

        GroupLayout jPanelSouthTempLayout = new GroupLayout(jPanelSouthTemp);
        jPanelSouthTemp.setLayout(jPanelSouthTempLayout);
        jPanelSouthTempLayout.setHorizontalGroup(
            jPanelSouthTempLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSouthTempLayout.createSequentialGroup()
                .addComponent(jButtonStartStop)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonCheckIP))
        );

        jPanelSouthTempLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {jButtonCheckIP, jButtonStartStop});

        jPanelSouthTempLayout.setVerticalGroup(
            jPanelSouthTempLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSouthTempLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(jButtonStartStop)
                .addComponent(jButtonCheckIP))
        );

        jPanelSouthTempLayout.linkSize(SwingConstants.VERTICAL, new Component[] {jButtonCheckIP, jButtonStartStop});

        jPanelSouth.add(jPanelSouthTemp);

        getContentPane().add(jPanelSouth, BorderLayout.SOUTH);

        jMenuFile.setText("File");
        jMenuFile.setMnemonic(KeyStroke.getKeyStroke(jMenuFile.getText().substring(0, 1).toUpperCase()).getKeyCode());

        jMenuItemFileOptions.setText("Options...");
        jMenuFile.add(jMenuItemFileOptions);
        jMenuFile.add(jSeparatorFile1);

        jMenuItemFileExit.setText("Exit");
        jMenuFile.add(jMenuItemFileExit);

        jMenuBar.add(jMenuFile);

        jMenuHelp.setText("Help");
        jMenuHelp.setMnemonic(KeyStroke.getKeyStroke(jMenuHelp.getText().substring(0, 1).toUpperCase()).getKeyCode());
        jMenuHelp.add(jMenuItemHelpAbout);

        jMenuBar.add(jMenuHelp);

        setJMenuBar(jMenuBar);

        pack();
    }

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
