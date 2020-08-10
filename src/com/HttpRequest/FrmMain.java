package com.HttpRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

/**
 * This is our Main Form
 * @author Ahmad Foroughi
 * @version 2.0
 */
public class FrmMain extends JFrame
{
    //Layout Manager (Changed in this task)
    private GridLayout layout = new GridLayout();
    //Panel 1
    public static Panel1 panel1 = new Panel1();
    //Panel 2
    public static Panel2 panel2 = new Panel2();
    //Panel 3
    public static Panel3 panel3 = new Panel3();
    //FRM OPTION
    private static FrmOption formOption = new FrmOption();

    /**
     * Create a new Main Form
     */
    public FrmMain()
    {
        //Set layout
        layout.setRows(1);
        layout.setColumns(3);
        getContentPane().setLayout(layout);

        //add menu bar
        addMenuBar(this);

        //add panels
        this.add(panel1);
        this.add(panel2);
        this.add(panel3);

        //set Size
        this.setSize(1100,575);
        //set default Close
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set icon
        ImageIcon icon = new ImageIcon("res/Pictures/icon.png");
        this.setIconImage(icon.getImage());
    }

    /**
     * add a new menu Bar to JFrame
     * @param frame is our JFrame -> is not necessary
     */
    public void addMenuBar(JFrame frame)
    {
        JMenuBar menuBar = new JMenuBar();
        JMenu application = new JMenu("Application");
        JMenu view = new JMenu("View");
        JMenu help = new JMenu("Help");
        //=====================================================================================
        application.setMnemonic('A');
        view.setMnemonic('V');
        help.setMnemonic('H');
        //=====================================================================================
        JMenuItem about = new JMenuItem("About");
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, Event.CTRL_MASK));
        JMenuItem Help  = new JMenuItem("Help");
        Help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, Event.CTRL_MASK));
        //=====================================================================================
        //Show About me details
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null,"Ahmad Foroughi\n ID=9831095","About",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        //Show Help Settings
        Help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null,"Help:\n" +
                        "-url [url] - default: http://google.com \n" +
                        " -i [show headers] \n" +
                        " -M [Method Name]" +
                        " -H [Headers list example : \"key1:value1;key2:value2\" \n" +
                        " -Q [Queries list example : \"key1:value1;key2:value2\" \n" +
                        " -J [JSON] -> MUST‌ be in json format example: {\"FirstName\":\"value\",\"LastName\":\"value\"} REMEMBER: escape (\\n) is important for JSON\n" +
                        " -D [FormURLEncoded] -> Must be in formData format example: firstName=ahmad&lastName=foroughi \n" +
                        " -f [auto redirect] \n" +
                        " -O [fileName or address] , -O none -> (Create a Random Name) \n" +
                        " -S [save requests options] \n" +
                        " --list [show requests] \n" +
                        " --fire [requests id] \n"+
                        "Coded by Ahmad Foroughi\n\n","HELP",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        help.add(about);
        help.add(Help);
        //=====================================================================================
        JCheckBoxMenuItem toggleFullScreen = new JCheckBoxMenuItem("Toggle Full Screen");
        toggleFullScreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, Event.ALT_MASK));
        toggleFullScreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try
                {
                    if(toggleFullScreen.isSelected())
                    {
                        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        frame.setUndecorated(true);
                    }
                    else
                    {
                        frame.setExtendedState(JFrame.NORMAL);
                    }
                }
                catch (java.awt.IllegalComponentStateException ex)
                {
                    //Do Nothing
                }

                //Back To 1100*575
            }
        });
        //===================================================================================== useless
        JCheckBoxMenuItem toggleSideBar = new JCheckBoxMenuItem("Toggle Sidebar");
        toggleSideBar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, Event.ALT_MASK));
        toggleSideBar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (toggleSideBar.isSelected())
                {
                    panel1.setVisible(false);
                    repaint();
                    revalidate();
                }
                else
                {
                    panel1.setVisible(true);
                    repaint();
                    revalidate();
                }
            }
        });
        //=====================================================================================
        view.add(toggleFullScreen);
        view.add(toggleSideBar);
        //=====================================================================================
        JMenuItem option = new JMenuItem("Option");
        option.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK));
        option.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try
                {
                    String trayActive = "tray->false";
                    String autoRedirect = "auto->false";
                    FileInputStream fis=new FileInputStream("Settings");
                    Scanner sc=new Scanner(fis);
                    String theme = sc.nextLine();
                    formOption.getComboBox().setSelectedItem(theme);//Set selected theme
                    trayActive = sc.nextLine();
                    if (trayActive.equals("tray->false"))//set selected trayActive
                    {
                        formOption.getChkHide().setSelected(false);
                    }
                    else
                    {
                        formOption.getChkHide().setSelected(true);
                    }
                    autoRedirect = sc.nextLine();
                    if (autoRedirect.equals("auto->false"))
                    {
                        //Active Auto Redirect
                        formOption.getChkFollowRedirect().setSelected(false);
                    }
                    else
                    {
                        //deactivate auto redirect
                        formOption.getChkFollowRedirect().setSelected(true);
                    }
                    sc.close();
//                    fis.close(); //Im not sure about this one :)
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                    System.exit(-1);
                }
                formOption.showFrm();
                formOption.getjButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        ChangeTheme.change(frame,formOption);

                        //Add System Tray
                        try
                        {
                            FileWriter fw=new FileWriter("Settings");
                            String toWrite= "";
                            if (formOption.getChkHide().isSelected())
                            {
                                if (formOption.getChkFollowRedirect().isSelected())
                                {
                                    toWrite = formOption.getComboBox().getSelectedItem()+"\n"+"tray->true"+"\n"+"auto->true";
                                    FrmMain.panel2.setRedirectActive(true);
                                }
                                else
                                {
                                    toWrite = formOption.getComboBox().getSelectedItem()+"\n"+"tray->true"+"\n"+"auto->false";
                                    FrmMain.panel2.setRedirectActive(false);
                                }

                            }
                            else
                            {
                                if (formOption.getChkFollowRedirect().isSelected())
                                {
                                    toWrite = formOption.getComboBox().getSelectedItem()+"\n"+"tray->false"+"\n"+"auto->true";
                                    FrmMain.panel2.setRedirectActive(true);
                                }
                                else
                                {
                                    toWrite = formOption.getComboBox().getSelectedItem()+"\n"+"tray->false"+"\n"+"auto->false";
                                    FrmMain.panel2.setRedirectActive(false);
                                }
                            }
                            fw.write(toWrite);
                            fw.close();
                        }
                        catch(Exception e)
                        {
                            System.out.println(e);
                        }
                        FrmMain.super.setSize(1100,575);
                    }
                });
            }
        });
        //=====================================================================================
        JMenuItem exit = new JMenuItem("Save & Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, Event.ALT_MASK));
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int result = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to quit (You can save only from here) ?",
                        "Confirm Quit", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION){
                    //Save Requests
                    try {
                        FileOutputStream requestsOutput = new FileOutputStream("savedRequests.bin");
                        ObjectOutputStream oos = new ObjectOutputStream(requestsOutput);
                        oos.writeObject(panel1.getRequestsList());
                        oos.close();
                    } catch (Exception e) {
                        System.out.println("There is no Save :).");
                    }
                    System.exit(0);
                }
            }
        });
        //=====================================================================================
        application.add(option);
        application.add(exit);
        //=====================================================================================
        menuBar.add(application);
        menuBar.add(view);
        menuBar.add(help);
        //=====================================================================================
        frame.setJMenuBar(menuBar);
    }

}