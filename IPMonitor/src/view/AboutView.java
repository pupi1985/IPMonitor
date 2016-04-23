/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package view;

import model.extras.AboutInformation;
import java.awt.*;
import java.text.*;
import javax.swing.*;

public class AboutView extends JDialog {
    
    public AboutView(Frame owner) {
        super(owner, true);
        initComponents();
        setLocationRelativeTo(null);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelMain = new javax.swing.JPanel();
        jPanelMainBorder = new javax.swing.JPanel();
        javax.swing.JPanel jPanelTemp = new javax.swing.JPanel();
        javax.swing.JLabel jLabelImageField = new javax.swing.JLabel();
        javax.swing.JLabel jLabelNameField = new javax.swing.JLabel();
        javax.swing.JLabel jLabelVersion = new javax.swing.JLabel();
        javax.swing.JLabel jLabelVersionField = new javax.swing.JLabel();
        javax.swing.JLabel jLabelDate = new javax.swing.JLabel();
        javax.swing.JLabel jLabelDateField = new javax.swing.JLabel();
        javax.swing.JLabel jLabelDeveloper = new javax.swing.JLabel();
        javax.swing.JLabel jLabelDeveloperField = new javax.swing.JLabel();
        javax.swing.JLabel jLabelURL = new javax.swing.JLabel();
        jLabelURLField = new javax.swing.JLabel();
        jPanelConfirmation = new view.panels.JPanelConfirmation(false, false);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("About IP Monitor");
        setResizable(false);

        jPanelMainBorder.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelImageField.setIcon(new ImageIcon(AboutInformation.getInstance().getImage()));

        jLabelNameField.setFont(jLabelNameField.getFont().deriveFont(jLabelNameField.getFont().getStyle() | java.awt.Font.BOLD, jLabelNameField.getFont().getSize()+9));
        jLabelNameField.setText(AboutInformation.getInstance().getName());

        jLabelVersion.setFont(jLabelVersion.getFont().deriveFont((jLabelVersion.getFont().getStyle() | java.awt.Font.ITALIC)));
        jLabelVersion.setLabelFor(jLabelVersionField);
        jLabelVersion.setText("Version:");

        jLabelVersionField.setFont(jLabelVersionField.getFont().deriveFont(jLabelVersionField.getFont().getStyle() | java.awt.Font.BOLD));
        jLabelVersionField.setText(AboutInformation.getInstance().getVersion());

        jLabelDate.setFont(jLabelDate.getFont().deriveFont((jLabelDate.getFont().getStyle() | java.awt.Font.ITALIC)));
        jLabelDate.setText("Date:");

        jLabelDateField.setFont(jLabelDateField.getFont().deriveFont(jLabelDateField.getFont().getStyle() | java.awt.Font.BOLD));
        jLabelDateField.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(AboutInformation.getInstance().getDate()));

        jLabelDeveloper.setFont(jLabelDeveloper.getFont().deriveFont((jLabelDeveloper.getFont().getStyle() | java.awt.Font.ITALIC)));
        jLabelDeveloper.setText("Developed by:");

        jLabelDeveloperField.setFont(jLabelDeveloperField.getFont().deriveFont(jLabelDeveloperField.getFont().getStyle() | java.awt.Font.BOLD));
        jLabelDeveloperField.setText(AboutInformation.getInstance().getDeveloper());

        jLabelURL.setFont(jLabelURL.getFont().deriveFont((jLabelURL.getFont().getStyle() | java.awt.Font.ITALIC)));
        jLabelURL.setText("URL:");

        jLabelURLField.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jLabelURLField.setFont(jLabelURLField.getFont().deriveFont(jLabelURLField.getFont().getStyle() | java.awt.Font.BOLD));
        jLabelURLField.setForeground(new java.awt.Color(51, 102, 255));
        jLabelURLField.setText(AboutInformation.getInstance().getVisualUrl());

        javax.swing.GroupLayout jPanelTempLayout = new javax.swing.GroupLayout(jPanelTemp);
        jPanelTemp.setLayout(jPanelTempLayout);
        jPanelTempLayout.setHorizontalGroup(
            jPanelTempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTempLayout.createSequentialGroup()
                .addGroup(jPanelTempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelVersion, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelDate, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelDeveloper, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelURL, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelVersionField)
                    .addComponent(jLabelDateField)
                    .addComponent(jLabelDeveloperField)
                    .addComponent(jLabelURLField)))
            .addGroup(jPanelTempLayout.createSequentialGroup()
                .addComponent(jLabelImageField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelNameField))
        );
        jPanelTempLayout.setVerticalGroup(
            jPanelTempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTempLayout.createSequentialGroup()
                .addGroup(jPanelTempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelNameField)
                    .addComponent(jLabelImageField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelVersion)
                    .addComponent(jLabelVersionField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDateField)
                    .addComponent(jLabelDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDeveloper)
                    .addComponent(jLabelDeveloperField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelURL)
                    .addComponent(jLabelURLField)))
        );

        javax.swing.GroupLayout jPanelMainBorderLayout = new javax.swing.GroupLayout(jPanelMainBorder);
        jPanelMainBorder.setLayout(jPanelMainBorderLayout);
        jPanelMainBorderLayout.setHorizontalGroup(
            jPanelMainBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelMainBorderLayout.setVerticalGroup(
            jPanelMainBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelMainBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelMainBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanelMain, java.awt.BorderLayout.CENTER);
        getContentPane().add(jPanelConfirmation, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelURLField;
    private view.panels.JPanelConfirmation jPanelConfirmation;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JPanel jPanelMainBorder;
    // End of variables declaration//GEN-END:variables
    
    public JLabel getJLabelURLField() {
        return jLabelURLField;
    }
    
    public JButton getJButtonOk() {
        return jPanelConfirmation.getJButtonOk();
    }    
}
