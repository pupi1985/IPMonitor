/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package controller.extras;

import java.awt.event.*;
import javax.swing.*;

public class InfoActionListener implements ActionListener {

    private String title;
    private String text;

    public InfoActionListener(String title, boolean ipOnly) {
        this.title = title;
        text = "Use the following expressions to get the correspondig result:\n" +
                "%OLDIP% = Shows the previous IP\n" +
                "%NEWIP% = Shows the current IP";
        if (!ipOnly) {
            text += "\n" +
                    "%DATE% = Shows the IP change date\n" +
                    "%TIME% = Shows the IP change time";
        }
    }

    public void actionPerformed(ActionEvent event) {
        JOptionPane.showMessageDialog(
                null, text, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
