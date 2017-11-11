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

    private AboutInformation aboutInformation;

    private JLabel jLabelProjectLinkField;
    private JLabel jLabelDonateLinkField;
    private JPanelConfirmation jPanelConfirmation;
    private JPanel jPanelMain;
    private JPanel jPanelMainBorder;

    public AboutView(Frame owner, AboutInformation aboutInformation) {
        super(owner, true);
        this.aboutInformation = aboutInformation;
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
        JLabel jLabelProjectLink = new JLabel();
        JLabel jLabelDonateLink = new JLabel();
        jLabelProjectLinkField = new JLabel();
        jLabelDonateLinkField = new JLabel();
        jPanelConfirmation = new JPanelConfirmation(true, false, false);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("About IP Monitor");
        setResizable(false);

        Font labelFont = UIManager.getFont("Label.font");
        Font labelFontBold = labelFont.deriveFont(labelFont.getStyle() | Font.BOLD);
        Color linkColor = new Color(51, 102, 255);

        jPanelMainBorder.setBorder(BorderFactory.createEtchedBorder());

        jLabelImageField.setIcon(new ImageIcon(aboutInformation.getImage()));

        jLabelNameField.setFont(labelFontBold.deriveFont((float) labelFontBold.getSize() + 9));
        jLabelNameField.setText(aboutInformation.getName());

        jLabelVersion.setLabelFor(jLabelVersionField);
        jLabelVersion.setText("Version:");

        jLabelVersionField.setFont(labelFontBold);
        jLabelVersionField.setText(aboutInformation.getVersion());

        jLabelDate.setText("Release date:");

        jLabelDateField.setFont(labelFontBold);
        jLabelDateField.setText(
                DateFormat.getDateInstance(DateFormat.MEDIUM).format(aboutInformation.getDate()));

        jLabelDeveloper.setText("Developed by:");

        jLabelDeveloperField.setFont(labelFontBold);
        jLabelDeveloperField.setText(aboutInformation.getDeveloper());

        jLabelProjectLink.setText("Project URL:");

        jLabelProjectLinkField.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jLabelProjectLinkField.setFont(labelFontBold);
        jLabelProjectLinkField.setForeground(linkColor);
        jLabelProjectLinkField.setText(aboutInformation.getProjectLinkText());

        jLabelDonateLink.setText("Support this project:");

        jLabelDonateLinkField.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jLabelDonateLinkField.setFont(labelFontBold);
        jLabelDonateLinkField.setForeground(linkColor);
        jLabelDonateLinkField.setText(aboutInformation.getDonateLinkText());

        GroupLayout jPanelTempLayout = new GroupLayout(jPanelTemp);
        jPanelTemp.setLayout(jPanelTempLayout);
        jPanelTempLayout.setHorizontalGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanelTempLayout.createSequentialGroup()
                        .addGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelVersion, GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabelDate, GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabelDeveloper, GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabelProjectLink, GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabelDonateLink, GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelVersionField).addComponent(jLabelDateField)
                                .addComponent(jLabelDeveloperField).addComponent(jLabelProjectLinkField)
                                .addComponent(jLabelDonateLinkField)))
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
                                .addComponent(jLabelProjectLink).addComponent(jLabelProjectLinkField))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelTempLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelDonateLink).addComponent(jLabelDonateLinkField))));

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

    public JLabel getJLabelProjectLinkField() {
        return jLabelProjectLinkField;
    }

    public JLabel getJLabelDonateLinkField() {
        return jLabelDonateLinkField;
    }

    public JButton getJButtonOk() {
        return jPanelConfirmation.getJButtonOk();
    }
}
