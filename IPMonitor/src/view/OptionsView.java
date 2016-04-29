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
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import model.ipmonitor.IPMonitor;
import model.notification.performers.AudioPerformer;
import model.service.ServiceManager;
import view.options.panels.JPanelOptionsInterface;
import view.options.panels.JPanelOptionsLogging;
import view.options.panels.JPanelOptionsMonitor;
import view.options.panels.JPanelOptionsNotification;
import view.options.panels.JPanelOptionsService;
import view.panels.JPanelConfirmation;

public class OptionsView extends JDialog {
	
    private JPanelConfirmation jPanelConfirmation;
    private JPanelOptionsInterface jPanelOptionsInterface;
    private JPanelOptionsLogging jPanelOptionsLogging;
    private JPanelOptionsMonitor jPanelOptionsMonitor;
    private JPanelOptionsNotification jPanelOptionsNotification;
    private JPanelOptionsService jPanelOptionsService;
    
    private IPMonitor ipMonitor;

    public OptionsView(Frame owner, IPMonitor ipMonitor) {
        super(owner, true);
        this.ipMonitor = ipMonitor;
        addWindowListener(new OptionsViewWindowAdapter());
        initComponents();
        getRootPane().setDefaultButton(getJButtonOk());
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        JTabbedPane jTabbedPaneOptions = new JTabbedPane();
        JPanel jPanelOptionsMonitorTemp = new JPanel();
        jPanelOptionsMonitor = new JPanelOptionsMonitor(ipMonitor);
        JPanel jPanelOptionsNotificationTemp = new JPanel();
        jPanelOptionsNotification = new JPanelOptionsNotification(ipMonitor);
        JPanel jPanelOptionsInterfaceTemp = new JPanel();
        jPanelOptionsInterface = new JPanelOptionsInterface();
        JPanel jPanelOptionsServicesTemp = new JPanel();
        jPanelOptionsService = new JPanelOptionsService();
        JPanel jPanelOptionsLoggingTemp = new JPanel();
        jPanelOptionsLogging = new JPanelOptionsLogging();
        jPanelConfirmation = new JPanelConfirmation(true, true);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Options");
        setResizable(false);

        jTabbedPaneOptions.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        jPanelOptionsMonitorTemp.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanelOptionsMonitorTemp.add(jPanelOptionsMonitor, gridBagConstraints);

        jTabbedPaneOptions.addTab("Monitor", jPanelOptionsMonitorTemp);

        jPanelOptionsNotificationTemp.setLayout(new GridBagLayout());
        jPanelOptionsNotificationTemp.add(jPanelOptionsNotification, new GridBagConstraints());

        jTabbedPaneOptions.addTab("Notification", jPanelOptionsNotificationTemp);

        jPanelOptionsInterfaceTemp.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        jPanelOptionsInterfaceTemp.add(jPanelOptionsInterface, gridBagConstraints);

        jTabbedPaneOptions.addTab("Interface", jPanelOptionsInterfaceTemp);

        jPanelOptionsServicesTemp.setLayout(new GridBagLayout());
        jPanelOptionsServicesTemp.add(jPanelOptionsService, new GridBagConstraints());

        try {

            jTabbedPaneOptions.addTab(ServiceManager.getInstance().getService().getServiceName(), jPanelOptionsServicesTemp);
        } catch (Exception e) {
        }

        jPanelOptionsLoggingTemp.setLayout(new GridBagLayout());
        jPanelOptionsLoggingTemp.add(jPanelOptionsLogging, new GridBagConstraints());

        jTabbedPaneOptions.addTab("Logging", jPanelOptionsLoggingTemp);

        getContentPane().add(jTabbedPaneOptions, BorderLayout.CENTER);
        getContentPane().add(jPanelConfirmation, BorderLayout.SOUTH);

        pack();
    }

    public JPanelOptionsInterface getJPanelOptionsInterface() {
        return jPanelOptionsInterface;
    }

    public JPanelOptionsMonitor getJPanelOptionsMonitor() {
        return jPanelOptionsMonitor;
    }

    public JPanelOptionsNotification getJPanelOptionsNotification() {
        return jPanelOptionsNotification;
    }

    public JPanelOptionsService getJPanelOptionsService() {
        return jPanelOptionsService;
    }

    public JPanelOptionsLogging getJPanelOptionsLogging() {
        return jPanelOptionsLogging;
    }

    public JButton getJButtonOk() {
        return jPanelConfirmation.getJButtonOk();
    }

    public JButton getJButtonCancel() {
        return jPanelConfirmation.getJButtonCancel();
    }

    public JButton getJButtonApply() {
        return jPanelConfirmation.getJButtonApply();
    }

    public JPanel getJPanelOptionsServiceInnerPanel() {
        return jPanelOptionsService.getJPanelInnerPanel();
    }

    private class OptionsViewWindowAdapter extends WindowAdapter {

        public void windowClosing(WindowEvent event) {
            AudioPerformer.getInstance().stop();
        }

        public void windowClosed(WindowEvent event) {
            AudioPerformer.getInstance().stop();
        }
    }
}
