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

package controller.options;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;

import view.options.MailTestView;

public class MailTestController {

    private MailTestView mailTestView;

    public MailTestController(JDialog owner, String debugOutputText) {
        ActionListener OkAction = new OkAction();

        mailTestView = new MailTestView(owner);
        mailTestView.getJButtonOk().addActionListener(OkAction);
        mailTestView.getRootPane().registerKeyboardAction(OkAction, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
        mailTestView.getJTextAreaDebugOutput().setText(debugOutputText);
        mailTestView.setVisible(true);
    }

    private class OkAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            mailTestView.dispose();
        }
    }
}
