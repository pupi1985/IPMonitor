/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package view.options;

import controller.extras.*;
import javax.swing.*;
import model.notification.configuration.*;

public class VisualConfigurationView extends JDialog {

    public VisualConfigurationView(JDialog owner) {
        super(owner, true);
        initComponents();
        getRootPane().setDefaultButton(getJButtonOk());
        setLocationRelativeTo(null);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel jPanelCenter = new javax.swing.JPanel();
        javax.swing.JPanel jPanelCenterBorder = new javax.swing.JPanel();
        javax.swing.JLabel jLabelTitle = new javax.swing.JLabel();
        jTextFieldTitle = new JTextField(VisualConfiguration.getInstance().getTitle(), 25);
        javax.swing.JLabel jLabelText = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPaneText = new javax.swing.JScrollPane();
        jTextAreaText = new JTextArea(VisualConfiguration.getInstance().getText());
        jTextAreaText.setFont(jTextFieldTitle.getFont());
        javax.swing.JLabel jLabelIcon = new javax.swing.JLabel();
        MessageTypeModel messageTypeModel = new MessageTypeModel();
        messageTypeModel.setSelectedItem(messageTypeModel.getMessageTypeWrapper(VisualConfiguration.getInstance().getIcon()));
        jComboBoxIcons = new JComboBox(messageTypeModel);
        jButtonInfo = new view.extras.JButtonInfo();
        jPanelConfirmation = new view.panels.JPanelConfirmation(true, true);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Visual notification configuration"); // NOI18N

        jPanelCenterBorder.setBorder(javax.swing.BorderFactory.createTitledBorder("Enter the data to pop up"));

        jLabelTitle.setText("Title:"); // NOI18N

        jTextFieldTitle.setColumns(40);

        jLabelText.setText("Text:"); // NOI18N

        jTextAreaText.setColumns(40);
        jTextAreaText.setRows(10);
        jScrollPaneText.setViewportView(jTextAreaText);

        jLabelIcon.setText("Icon:"); // NOI18N

        javax.swing.GroupLayout jPanelCenterBorderLayout = new javax.swing.GroupLayout(jPanelCenterBorder);
        jPanelCenterBorder.setLayout(jPanelCenterBorderLayout);
        jPanelCenterBorderLayout.setHorizontalGroup(
            jPanelCenterBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCenterBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCenterBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTitle)
                    .addComponent(jLabelText)
                    .addComponent(jLabelIcon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCenterBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldTitle)
                    .addComponent(jScrollPaneText)
                    .addGroup(jPanelCenterBorderLayout.createSequentialGroup()
                        .addComponent(jComboBoxIcons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 242, Short.MAX_VALUE)
                        .addComponent(jButtonInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelCenterBorderLayout.setVerticalGroup(
            jPanelCenterBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCenterBorderLayout.createSequentialGroup()
                .addGroup(jPanelCenterBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTitle)
                    .addComponent(jTextFieldTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCenterBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelText)
                    .addComponent(jScrollPaneText, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCenterBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIcon)
                    .addComponent(jComboBoxIcons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelCenterLayout = new javax.swing.GroupLayout(jPanelCenter);
        jPanelCenter.setLayout(jPanelCenterLayout);
        jPanelCenterLayout.setHorizontalGroup(
            jPanelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCenterBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelCenterLayout.setVerticalGroup(
            jPanelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCenterBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanelCenter, java.awt.BorderLayout.CENTER);
        getContentPane().add(jPanelConfirmation, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.extras.JButtonInfo jButtonInfo;
    private javax.swing.JComboBox jComboBoxIcons;
    private view.panels.JPanelConfirmation jPanelConfirmation;
    private javax.swing.JTextArea jTextAreaText;
    private javax.swing.JTextField jTextFieldTitle;
    // End of variables declaration//GEN-END:variables
    public JTextField getJTextFieldTitle() {
        return jTextFieldTitle;
    }

    public JTextArea getJTextAreaText() {
        return jTextAreaText;
    }

    public JComboBox getJComboBoxIcons() {
        if (jComboBoxIcons == null) {
            MessageTypeModel messageTypeModel = new MessageTypeModel();
            messageTypeModel.setSelectedItem(messageTypeModel.getMessageTypeWrapper(VisualConfiguration.getInstance().getIcon()));
            jComboBoxIcons = new JComboBox(messageTypeModel);
        }
        return jComboBoxIcons;
    }

    public JButton getJButtonInfo() {
        return jButtonInfo;
    }

    public JButton getJButtonApply() {
        return jPanelConfirmation.getJButtonApply();
    }

    public JButton getJButtonOk() {
        return jPanelConfirmation.getJButtonOk();
    }

    public JButton getJButtonCancel() {
        return jPanelConfirmation.getJButtonCancel();
    }
}
