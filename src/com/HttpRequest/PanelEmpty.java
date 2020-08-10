package com.HttpRequest;

import javax.swing.*;
import java.awt.*;

/**
 * This is an Empty Panel
 * @author Ahmad Foroughi
 * @version 1.0
 */
public class PanelEmpty extends JPanel
{
    //Fields
    private JLabel label;

    /**
     * Create a new Empty Panel
     */
    public PanelEmpty()
    {
        setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(new Color(255,255,255)));
        this.setSize(427,575);
        label = new JLabel("Please Create a New Request",SwingConstants.CENTER);
        add(label,BorderLayout.CENTER);
    }

    /**
     * @return label
     */
    public JLabel getLabel() {
        return label;
    }

    /**
     * @param label is a JLabel
     */
    public void setLabel(JLabel label) {
        this.label = label;
    }
}
