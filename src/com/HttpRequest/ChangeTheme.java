package com.HttpRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class changes theme
 * @author Ahmad
 * @version 1.0
 */
public class ChangeTheme {

    /**
     * Change theme with given frame and formOption
     * @param frame is JFrame
     * @param formOption is a FrmOption (JFRAME)
     */
    public static void change(JFrame frame, FrmOption formOption)
    {
        try
        {
            if (formOption.getComboBox().getSelectedItem().equals("HiFiLookAndFeel"))
            {
                javax.swing.UIManager.setLookAndFeel(com.jtattoo.plaf.hifi.HiFiLookAndFeel.class.getName());
                SwingUtilities.updateComponentTreeUI(frame);
                frame.pack();
                SwingUtilities.updateComponentTreeUI(formOption);
                formOption.pack();

            }
            else if (formOption.getComboBox().getSelectedItem().equals("AcrylLookAndFeel"))
            {
                javax.swing.UIManager.setLookAndFeel(com.jtattoo.plaf.acryl.AcrylLookAndFeel.class.getName());
                SwingUtilities.updateComponentTreeUI(frame);
                frame.pack();
                SwingUtilities.updateComponentTreeUI(formOption);
                formOption.pack();

            }
            else if (formOption.getComboBox().getSelectedItem().equals("AeroLookAndFeel"))
            {
                javax.swing.UIManager.setLookAndFeel(com.jtattoo.plaf.aero.AeroLookAndFeel.class.getName());
                SwingUtilities.updateComponentTreeUI(frame);
                frame.pack();
                SwingUtilities.updateComponentTreeUI(formOption);
                formOption.pack();
            }
            else if (formOption.getComboBox().getSelectedItem().equals("AluminiumLookAndFeel"))
            {
                javax.swing.UIManager.setLookAndFeel(com.jtattoo.plaf.aluminium.AluminiumLookAndFeel.class.getName());
                SwingUtilities.updateComponentTreeUI(frame);
                frame.pack();
                SwingUtilities.updateComponentTreeUI(formOption);
                formOption.pack();
            }
            else if (formOption.getComboBox().getSelectedItem().equals("BernsteinLookAndFeel"))
            {
                javax.swing.UIManager.setLookAndFeel(com.jtattoo.plaf.bernstein.BernsteinLookAndFeel.class.getName());
                SwingUtilities.updateComponentTreeUI(frame);
                frame.pack();
                SwingUtilities.updateComponentTreeUI(formOption);
                formOption.pack();
            }
            else if (formOption.getComboBox().getSelectedItem().equals("FastLookAndFeel"))
            {
                javax.swing.UIManager.setLookAndFeel(com.jtattoo.plaf.fast.FastLookAndFeel.class.getName());
                SwingUtilities.updateComponentTreeUI(frame);
                frame.pack();
                SwingUtilities.updateComponentTreeUI(formOption);
                formOption.pack();
            }
            else if (formOption.getComboBox().getSelectedItem().equals("McWinLookAndFeel"))
            {
                javax.swing.UIManager.setLookAndFeel(com.jtattoo.plaf.mcwin.McWinLookAndFeel.class.getName());
                SwingUtilities.updateComponentTreeUI(frame);
                frame.pack();
                SwingUtilities.updateComponentTreeUI(formOption);
                formOption.pack();
            }
            else if (formOption.getComboBox().getSelectedItem().equals("MintLookAndFeel"))
            {
                javax.swing.UIManager.setLookAndFeel(com.jtattoo.plaf.mint.MintLookAndFeel.class.getName());
                SwingUtilities.updateComponentTreeUI(frame);
                frame.pack();
                SwingUtilities.updateComponentTreeUI(formOption);
                formOption.pack();
            }
            else if (formOption.getComboBox().getSelectedItem().equals("NoireLookAndFeel"))
            {
                javax.swing.UIManager.setLookAndFeel(com.jtattoo.plaf.noire.NoireLookAndFeel.class.getName());
                SwingUtilities.updateComponentTreeUI(frame);
                frame.pack();
                SwingUtilities.updateComponentTreeUI(formOption);
                formOption.pack();
            }
            else if (formOption.getComboBox().getSelectedItem().equals("SmartLookAndFeel"))
            {
                javax.swing.UIManager.setLookAndFeel(com.jtattoo.plaf.smart.SmartLookAndFeel.class.getName());
                SwingUtilities.updateComponentTreeUI(frame);
                frame.pack();
                SwingUtilities.updateComponentTreeUI(formOption);
                formOption.pack();
            }
            else if (formOption.getComboBox().getSelectedItem().equals("LunaLookAndFeel"))
            {
                javax.swing.UIManager.setLookAndFeel(com.jtattoo.plaf.luna.LunaLookAndFeel.class.getName());
                SwingUtilities.updateComponentTreeUI(frame);
                frame.pack();
                SwingUtilities.updateComponentTreeUI(formOption);
                formOption.pack();
            }
            else if (formOption.getComboBox().getSelectedItem().equals("TextureLookAndFeel"))
            {
                javax.swing.UIManager.setLookAndFeel(com.jtattoo.plaf.texture.TextureLookAndFeel.class.getName());
                SwingUtilities.updateComponentTreeUI(frame);
                frame.pack();
                SwingUtilities.updateComponentTreeUI(formOption);
                formOption.pack();
            }




        }
        catch (Exception exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * Change Theme in the beginning
     * @param theme is selected theme in Settings file
     */
    public static void changeInBeg(String theme)
    {
        try
        {
            if (theme.equals("HiFiLookAndFeel"))
            {
                javax.swing.UIManager.setLookAndFeel(com.jtattoo.plaf.hifi.HiFiLookAndFeel.class.getName());
            }
            else if (theme.equals("AcrylLookAndFeel"))
            {
                javax.swing.UIManager.setLookAndFeel(com.jtattoo.plaf.acryl.AcrylLookAndFeel.class.getName());

            }
            else if (theme.equals("AeroLookAndFeel"))
            {
                javax.swing.UIManager.setLookAndFeel(com.jtattoo.plaf.aero.AeroLookAndFeel.class.getName());
            }
            else if (theme.equals("AluminiumLookAndFeel"))
            {
                javax.swing.UIManager.setLookAndFeel(com.jtattoo.plaf.aluminium.AluminiumLookAndFeel.class.getName());
            }
            else if (theme.equals("BernsteinLookAndFeel"))
            {
                javax.swing.UIManager.setLookAndFeel(com.jtattoo.plaf.bernstein.BernsteinLookAndFeel.class.getName());
            }
            else if (theme.equals("FastLookAndFeel"))
            {
                javax.swing.UIManager.setLookAndFeel(com.jtattoo.plaf.fast.FastLookAndFeel.class.getName());
            }
            else if (theme.equals("McWinLookAndFeel"))
            {
                javax.swing.UIManager.setLookAndFeel(com.jtattoo.plaf.mcwin.McWinLookAndFeel.class.getName());
            }
            else if (theme.equals("MintLookAndFeel"))
            {
                javax.swing.UIManager.setLookAndFeel(com.jtattoo.plaf.mint.MintLookAndFeel.class.getName());
            }
            else if (theme.equals("NoireLookAndFeel"))
            {
                javax.swing.UIManager.setLookAndFeel(com.jtattoo.plaf.noire.NoireLookAndFeel.class.getName());
            }
            else if (theme.equals("SmartLookAndFeel"))
            {
                javax.swing.UIManager.setLookAndFeel(com.jtattoo.plaf.smart.SmartLookAndFeel.class.getName());
            }
            else if (theme.equals("LunaLookAndFeel"))
            {
                javax.swing.UIManager.setLookAndFeel(com.jtattoo.plaf.luna.LunaLookAndFeel.class.getName());
            }
            else if (theme.equals("TextureLookAndFeel"))
            {
                javax.swing.UIManager.setLookAndFeel(com.jtattoo.plaf.texture.TextureLookAndFeel.class.getName());
            }

        }
        catch (Exception exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * Active SystemTry
     * @param frmMain is our Main Form
     */
    public static void activeSystemTry(FrmMain frmMain)
    {

        if(!SystemTray.isSupported()){
            System.out.println("System tray is not supported !!! ");
            return ;
        }
        SystemTray systemTray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().getImage("icon.png");
        PopupMenu trayPopupMenu = new PopupMenu();
        MenuItem action = new MenuItem("Show Program");
        action.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (frmMain.isVisible())
                {
                    frmMain.setVisible(false);
                    action.setLabel("Show Program");
                }
                else
                {
                    frmMain.setVisible(true);
                    action.setLabel("Hide Program");
                }
            }
        });
        trayPopupMenu.add(action);
        MenuItem close = new MenuItem("Exit");
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        trayPopupMenu.add(close);
        TrayIcon trayIcon = new TrayIcon(image, "HTTP_REQUEST", trayPopupMenu);
        trayIcon.setImageAutoSize(true);
        try{
            systemTray.add(trayIcon);
        }catch(AWTException awtException){
            awtException.printStackTrace();
        }
    }

}
