/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
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
