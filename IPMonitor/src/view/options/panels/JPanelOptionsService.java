/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package view.options.panels;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import model.service.ServiceManager;

public class JPanelOptionsService extends JPanel {

	private JButton jButtonInstall;
	private JButton jButtonStart;
	private JButton jButtonStop;
	private JButton jButtonTest;
	private JButton jButtonUninstall;
	private JPanel jPanelTemp;

	public JPanelOptionsService() {
		initComponents();
	}

	private void initComponents() {
		jPanelTemp = new JPanel();
		jButtonInstall = new JButton();
		jButtonStart = new JButton();
		jButtonTest = new JButton();
		jButtonUninstall = new JButton();
		jButtonStop = new JButton();

		setLayout(new GridBagLayout());

		String serviceName = ServiceManager.getInstance().getService().getServiceName().toLowerCase();

		jButtonInstall.setText("Install " + serviceName);

		jButtonStart.setText("Start " + serviceName);

		jButtonTest.setText("Test " + serviceName);

		jButtonUninstall.setText("Uninstall " + serviceName);

		jButtonStop.setText("Stop " + serviceName);

		GroupLayout jPanelTempLayout = new GroupLayout(jPanelTemp);
		jPanelTemp.setLayout(jPanelTempLayout);
		jPanelTempLayout.setHorizontalGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanelTempLayout.createSequentialGroup().addContainerGap().addGroup(jPanelTempLayout
						.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
						.addComponent(jButtonStart, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jButtonInstall, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(jButtonTest, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(jButtonStop, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(jButtonUninstall, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addContainerGap()));

		jPanelTempLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {
				jButtonInstall, jButtonStart, jButtonStop, jButtonTest, jButtonUninstall
		});

		jPanelTempLayout.setVerticalGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanelTempLayout.createSequentialGroup().addContainerGap()
						.addGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(jButtonInstall).addComponent(jButtonUninstall))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(jButtonStart).addComponent(jButtonStop))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanelTempLayout
								.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jButtonTest))
						.addContainerGap()));

		jPanelTempLayout.linkSize(SwingConstants.VERTICAL, new Component[] {
				jButtonInstall, jButtonStart, jButtonStop, jButtonTest, jButtonUninstall
		});

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

	public JPanel getJPanelInnerPanel() {
		return jPanelTemp;
	}
}
