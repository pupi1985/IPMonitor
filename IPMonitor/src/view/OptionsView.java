/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package view;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import model.ipmonitor.IPMonitor;
import model.notification.performers.AudioPerformer;
import model.service.services.ServiceManager;
import view.options.panels.JPanelOptionsInterface;
import view.options.panels.JPanelOptionsLogging;
import view.options.panels.JPanelOptionsMonitor;
import view.options.panels.JPanelOptionsNotification;
import view.options.panels.JPanelOptionsService;

public class OptionsView extends JDialog {
	
    private view.panels.JPanelConfirmation jPanelConfirmation;
    private view.options.panels.JPanelOptionsInterface jPanelOptionsInterface;
    private view.options.panels.JPanelOptionsLogging jPanelOptionsLogging;
    private view.options.panels.JPanelOptionsMonitor jPanelOptionsMonitor;
    private view.options.panels.JPanelOptionsNotification jPanelOptionsNotification;
    private view.options.panels.JPanelOptionsService jPanelOptionsService;
    
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
        java.awt.GridBagConstraints gridBagConstraints;

        javax.swing.JTabbedPane jTabbedPaneOptions = new javax.swing.JTabbedPane();
        javax.swing.JPanel jPanelOptionsMonitorTemp = new javax.swing.JPanel();
        jPanelOptionsMonitor = new view.options.panels.JPanelOptionsMonitor(ipMonitor);
        javax.swing.JPanel jPanelOptionsNotificationTemp = new javax.swing.JPanel();
        jPanelOptionsNotification = new view.options.panels.JPanelOptionsNotification(ipMonitor);
        javax.swing.JPanel jPanelOptionsInterfaceTemp = new javax.swing.JPanel();
        jPanelOptionsInterface = new view.options.panels.JPanelOptionsInterface();
        javax.swing.JPanel jPanelOptionsServicesTemp = new javax.swing.JPanel();
        jPanelOptionsService = new view.options.panels.JPanelOptionsService();
        javax.swing.JPanel jPanelOptionsLoggingTemp = new javax.swing.JPanel();
        jPanelOptionsLogging = new view.options.panels.JPanelOptionsLogging();
        jPanelConfirmation = new view.panels.JPanelConfirmation(true, true);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Options");
        setResizable(false);

        jTabbedPaneOptions.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        jPanelOptionsMonitorTemp.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanelOptionsMonitorTemp.add(jPanelOptionsMonitor, gridBagConstraints);

        jTabbedPaneOptions.addTab("Monitor", jPanelOptionsMonitorTemp);

        jPanelOptionsNotificationTemp.setLayout(new java.awt.GridBagLayout());
        jPanelOptionsNotificationTemp.add(jPanelOptionsNotification, new java.awt.GridBagConstraints());

        jTabbedPaneOptions.addTab("Notification", jPanelOptionsNotificationTemp);

        jPanelOptionsInterfaceTemp.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanelOptionsInterfaceTemp.add(jPanelOptionsInterface, gridBagConstraints);

        jTabbedPaneOptions.addTab("Interface", jPanelOptionsInterfaceTemp);

        jPanelOptionsServicesTemp.setLayout(new java.awt.GridBagLayout());
        jPanelOptionsServicesTemp.add(jPanelOptionsService, new java.awt.GridBagConstraints());

        try {

            jTabbedPaneOptions.addTab(ServiceManager.getInstance().getService().getServiceName(), jPanelOptionsServicesTemp);
        } catch (Exception e) {
        }

        jPanelOptionsLoggingTemp.setLayout(new java.awt.GridBagLayout());
        jPanelOptionsLoggingTemp.add(jPanelOptionsLogging, new java.awt.GridBagConstraints());

        jTabbedPaneOptions.addTab("Logging", jPanelOptionsLoggingTemp);

        getContentPane().add(jTabbedPaneOptions, java.awt.BorderLayout.CENTER);
        getContentPane().add(jPanelConfirmation, java.awt.BorderLayout.SOUTH);

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
