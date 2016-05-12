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
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.text.DateFormat;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import model.extras.AboutInformation;
import view.panels.JPanelConfirmation;

public class AboutView extends JDialog {

    private JLabel jLabelURLField;
    private JPanelConfirmation jPanelConfirmation;
    private JPanel jPanelMain;
    private JPanel jPanelMainBorder;

    public AboutView(Frame owner) {
        super(owner, true);
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        jPanelMain = new JPanel();
        jPanelMainBorder = new JPanel();
        JPanel jPanelTemp = new JPanel();
        JLabel jLabelImageField = new JLabel();
        JLabel jLabelNameField = new JLabel();
        JLabel jLabelVersion = new JLabel();
        JLabel jLabelVersionField = new JLabel();
        JLabel jLabelDate = new JLabel();
        JLabel jLabelDateField = new JLabel();
        JLabel jLabelDeveloper = new JLabel();
        JLabel jLabelDeveloperField = new JLabel();
        JLabel jLabelURL = new JLabel();
        jLabelURLField = new JLabel();
        jPanelConfirmation = new JPanelConfirmation(true, false, false);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("About IP Monitor");
        setResizable(false);

        Font labelFont = UIManager.getFont("Label.font");
        Font labelFontItalic = labelFont.deriveFont(labelFont.getStyle() | Font.ITALIC);
        Font labelFontBold = labelFont.deriveFont(labelFont.getStyle() | Font.BOLD);

        jPanelMainBorder.setBorder(BorderFactory.createEtchedBorder());

        jLabelImageField.setIcon(new ImageIcon(AboutInformation.getInstance().getImage()));

        jLabelNameField.setFont(labelFontBold.deriveFont((float) labelFontBold.getSize() + 9));
        jLabelNameField.setText(AboutInformation.getInstance().getName());

        jLabelVersion.setFont(labelFontItalic);
        jLabelVersion.setLabelFor(jLabelVersionField);
        jLabelVersion.setText("Version:");

        jLabelVersionField.setFont(labelFontBold);
        jLabelVersionField.setText(AboutInformation.getInstance().getVersion());

        jLabelDate.setFont(labelFontItalic);
        jLabelDate.setText("Date:");

        jLabelDateField.setFont(labelFontBold);
        jLabelDateField.setText(
                DateFormat.getDateInstance(DateFormat.MEDIUM).format(AboutInformation.getInstance().getDate()));

        jLabelDeveloper.setFont(labelFontItalic);
        jLabelDeveloper.setText("Developed by:");

        jLabelDeveloperField.setFont(labelFontBold);
        jLabelDeveloperField.setText(AboutInformation.getInstance().getDeveloper());

        jLabelURL.setFont(labelFontItalic);
        jLabelURL.setText("URL:");

        jLabelURLField.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jLabelURLField.setFont(labelFontBold);
        jLabelURLField.setForeground(new Color(51, 102, 255));
        jLabelURLField.setText(AboutInformation.getInstance().getVisualUrl());

        GroupLayout jPanelTempLayout = new GroupLayout(jPanelTemp);
        jPanelTemp.setLayout(jPanelTempLayout);
        jPanelTempLayout.setHorizontalGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanelTempLayout.createSequentialGroup()
                        .addGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelVersion, GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabelDate, GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabelDeveloper, GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabelURL, GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelVersionField).addComponent(jLabelDateField)
                                .addComponent(jLabelDeveloperField).addComponent(jLabelURLField)))
                .addGroup(jPanelTempLayout.createSequentialGroup().addComponent(jLabelImageField)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabelNameField)));
        jPanelTempLayout.setVerticalGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanelTempLayout.createSequentialGroup()
                        .addGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(jLabelNameField).addComponent(jLabelImageField))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelVersion).addComponent(jLabelVersionField))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelDateField).addComponent(jLabelDate))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelDeveloper).addComponent(jLabelDeveloperField))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelURL).addComponent(jLabelURLField))));

        GroupLayout jPanelMainBorderLayout = new GroupLayout(jPanelMainBorder);
        jPanelMainBorder.setLayout(jPanelMainBorderLayout);
        jPanelMainBorderLayout
                .setHorizontalGroup(jPanelMainBorderLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelMainBorderLayout.createSequentialGroup().addContainerGap()
                                .addComponent(jPanelTemp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanelMainBorderLayout
                .setVerticalGroup(jPanelMainBorderLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelMainBorderLayout.createSequentialGroup().addContainerGap()
                                .addComponent(jPanelTemp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        GroupLayout jPanelMainLayout = new GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(jPanelMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanelMainLayout.createSequentialGroup().addContainerGap()
                        .addComponent(jPanelMainBorder, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanelMainLayout.setVerticalGroup(jPanelMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanelMainLayout.createSequentialGroup().addContainerGap()
                        .addComponent(jPanelMainBorder, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        getContentPane().add(jPanelMain, BorderLayout.CENTER);
        getContentPane().add(jPanelConfirmation, BorderLayout.PAGE_END);

        pack();
    }

    public JLabel getJLabelURLField() {
        return jLabelURLField;
    }

    public JButton getJButtonOk() {
        return jPanelConfirmation.getJButtonOk();
    }
}
