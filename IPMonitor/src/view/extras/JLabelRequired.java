/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package view.extras;

import java.awt.*;
import javax.swing.*;

public class JLabelRequired extends JLabel {

    public JLabelRequired() {
        setText("*");
        setForeground(Color.RED);
    }
}
