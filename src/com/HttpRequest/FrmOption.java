package com.HttpRequest;

import javax.swing.*;
import java.awt.*;

/**
 * This is Our Option Form
 * @author Ahmad Foroughi
 * @version  1.0
 */
public class FrmOption extends JFrame
{
    //Fields
    private JCheckBox chkFollowRedirect;
    private JCheckBox chkHide;
    private JComboBox<String> comboBox;
    private JButton jButton;

    /**
     * Create a new Option Form
     */
    public FrmOption()
    {
        //Set layout and size
        setLayout(new BorderLayout());
        setResizable(false);
        setSize(800,400);

        //set Icon
        ImageIcon icon = new ImageIcon("res/Pictures/Settings.png");
        setIconImage(icon.getImage());

        JButton settingsIntro = new JButton("Settings");
        settingsIntro.setEnabled(false);

        chkFollowRedirect = new JCheckBox("Follow Redirect   ");
        chkHide = new JCheckBox("System Tray Active");

        JLabel lblTheme = new JLabel("   Theme: ");
        String[] list = {"HiFiLookAndFeel" , "AcrylLookAndFeel" , "AeroLookAndFeel" , "AluminiumLookAndFeel","BernsteinLookAndFeel" , "FastLookAndFeel" ,  "McWinLookAndFeel","MintLookAndFeel","NoireLookAndFeel","SmartLookAndFeel","LunaLookAndFeel","TextureLookAndFeel"};
        comboBox = new JComboBox<>(list);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.add(chkFollowRedirect);
        panel.add(chkHide);
        panel.add(lblTheme);
        panel.add(comboBox);

        jButton = new JButton("Apply Changes");

        add(settingsIntro,BorderLayout.NORTH);
        add(panel,BorderLayout.CENTER);
        add(jButton,BorderLayout.SOUTH);
    }

    /**
     * @return jButton
     */
    public JButton getjButton() {
        return jButton;
    }

    /**
     * @return chkFollowRedirect
     */
    public JCheckBox getChkFollowRedirect() {
        return chkFollowRedirect;
    }

    /**
     * @return chkHide
     */
    public JCheckBox getChkHide() {
        return chkHide;
    }

    /**
     * @return comboBox
     */
    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    /**
     * Show Form
     */
    public void showFrm()
    {
        setVisible(true);
    }


}
