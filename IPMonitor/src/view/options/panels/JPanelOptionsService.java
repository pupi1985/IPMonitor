/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package view.options.panels;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import controller.extras.OSModel;
import model.configuration.ConfigurationManager;
import model.service.exceptions.OSNotSupportedException;
import model.service.os.AbstractOS;
import model.service.os.OSManager;
import model.service.os.Windows;
import model.service.services.ServiceManager;

public class JPanelOptionsService extends JPanel {
	
    private JButton jButtonInstall;
    private JButton jButtonStart;
    private JButton jButtonStop;
    private JButton jButtonTest;
    private JButton jButtonUninstall;
    private JComboBox<AbstractOS> jComboBoxOS;
    private JPanel jPanelTemp;

    private String serviceName;

    public JPanelOptionsService() {
        try {
            serviceName = ServiceManager.getInstance().getService().getServiceName().toLowerCase();
        } catch (OSNotSupportedException e) {
            serviceName = new Windows().getService().getServiceName().toLowerCase();
        }
        initComponents();
    }

    private void initComponents() {
        jPanelTemp = new JPanel();
        OSModel OSModel = new OSModel();
        jComboBoxOS = new JComboBox<AbstractOS>(OSModel);
        if (jComboBoxOS.getModel().getSize() == 1) {
            jComboBoxOS.setEnabled(false);
        }
        jButtonInstall = new JButton();
        jButtonStart = new JButton();
        jButtonTest = new JButton();
        jButtonUninstall = new JButton();
        jButtonStop = new JButton();

        setLayout(new GridBagLayout());

        jButtonInstall.setText("Install " + serviceName);

        jButtonStart.setText("Start " + serviceName);

        jButtonTest.setText("Test " + serviceName);

        jButtonUninstall.setText("Uninstall " + serviceName);

        jButtonStop.setText("Stop " + serviceName);

        GroupLayout jPanelTempLayout = new GroupLayout(jPanelTemp);
        jPanelTemp.setLayout(jPanelTempLayout);
        jPanelTempLayout.setHorizontalGroup(
            jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTempLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButtonStart, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonInstall, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxOS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonStop, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonTest, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonUninstall, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanelTempLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {jButtonInstall, jButtonStart, jButtonStop, jButtonTest, jButtonUninstall, jComboBoxOS});

        jPanelTempLayout.setVerticalGroup(
            jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTempLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxOS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonTest))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonInstall)
                    .addComponent(jButtonUninstall))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonStart)
                    .addComponent(jButtonStop))
                .addContainerGap())
        );

        jPanelTempLayout.linkSize(SwingConstants.VERTICAL, new Component[] {jButtonInstall, jButtonStart, jButtonStop, jButtonTest, jButtonUninstall, jComboBoxOS});

        try {
            jComboBoxOS.getModel().setSelectedItem((OSManager.getInstance().getClassForID(ConfigurationManager.getInstance().getOsId())).newInstance());
        } catch (Exception e) {
            jComboBoxOS.setSelectedIndex(0);
        }

        add(jPanelTemp, new GridBagConstraints());
    }

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

    public JComboBox<AbstractOS> getJComboBoxOS() {
        return jComboBoxOS;
    }

    public JPanel getJPanelInnerPanel() {
        return jPanelTemp;
    }
}
