/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package view.options.panels;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.extras.TimeUnitConverter;
import model.configuration.ConfigurationManager;
import model.ipmonitor.IPMonitor;

public class JPanelOptionsMonitor extends JPanel {
    
	private javax.swing.JCheckBox jCheckBoxAutostart;
	private javax.swing.JTextField jTextFieldHours;
	private javax.swing.JTextField jTextFieldMinutes;
	private javax.swing.JTextField jTextFieldSeconds;
	private javax.swing.JTextField jTextFieldURL;

	private IPMonitor ipMonitor = null;
	
	public JPanelOptionsMonitor() {
    }
    
    public JPanelOptionsMonitor(IPMonitor ipMonitor) {
        this.ipMonitor = ipMonitor;
        initComponents();
    }

    private void initComponents() {
        javax.swing.JLabel jLabelInterval = new javax.swing.JLabel();
        javax.swing.JLabel jLabelCheckingURL = new javax.swing.JLabel();
        TimeUnitConverter timeUnitConverter = new TimeUnitConverter(ipMonitor.getInterval());
        jTextFieldHours = new javax.swing.JTextField();
        javax.swing.JLabel jLabelColon1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabelColon2 = new javax.swing.JLabel();
        jTextFieldMinutes = new javax.swing.JTextField();
        jTextFieldSeconds = new javax.swing.JTextField();
        jTextFieldURL = new javax.swing.JTextField();
        javax.swing.JLabel jLabelHMS = new javax.swing.JLabel();
        jCheckBoxAutostart = new javax.swing.JCheckBox();

        jLabelInterval.setText("Interval:");

        jLabelCheckingURL.setText("Checking URL:");

        jTextFieldHours.setColumns(3);
        jTextFieldHours.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldHours.setText(String.valueOf(timeUnitConverter.getHours()));

        jLabelColon1.setText(":");

        jLabelColon2.setText(":");

        jTextFieldMinutes.setColumns(3);
        jTextFieldMinutes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldMinutes.setText(String.valueOf(timeUnitConverter.getMinutes()));

        jTextFieldSeconds.setColumns(3);
        jTextFieldSeconds.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldSeconds.setText(String.valueOf(timeUnitConverter.getSeconds()));

        jTextFieldURL.setText(ipMonitor.getUrl());

        jLabelHMS.setText("(H:M:S)");

        jCheckBoxAutostart.setSelected(ConfigurationManager.getInstance().isAutostart());
        jCheckBoxAutostart.setText("Autostart when IP Monitor launches");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelCheckingURL)
                            .addComponent(jLabelInterval))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelColon1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelColon2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelHMS))
                            .addComponent(jTextFieldURL, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)))
                    .addComponent(jCheckBoxAutostart, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelColon1)
                    .addComponent(jTextFieldMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelColon2)
                    .addComponent(jTextFieldSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelHMS)
                    .addComponent(jLabelInterval))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCheckingURL)
                    .addComponent(jTextFieldURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
