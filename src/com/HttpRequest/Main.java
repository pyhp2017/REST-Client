package com.HttpRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * This is our Main Class
 * @author Ahmad Foroughi
 * @version 1.0
 */
public class Main {


    /**
     * Main Method
     * @param args (NOTHING)
     */
    public static void main(String[] args)
    {
        String trayActive = "tray->false";
        String follow = "auto->false";
        //Set Look And Feel
        try
        {
            FileInputStream fis=new FileInputStream("Settings");
            Scanner sc=new Scanner(fis);
            String theme = sc.nextLine();
            trayActive = sc.nextLine();
            follow = sc.nextLine();
            ChangeTheme.changeInBeg(theme);
            sc.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.exit(-1);
        }

        FrmMain frmMain = new FrmMain();

        //Load given Settings
        if (trayActive.equals("tray->true"))
        {
            ChangeTheme.activeSystemTry(frmMain);
        }
        else
        {
            frmMain.setVisible(true);
        }
        if (follow.equals("auto->true"))
        {
            //active follow redirect
            FrmMain.panel2.setRedirectActive(true);
        }
        else
        {
            //deactivate
            FrmMain.panel2.setRedirectActive(false);
        }

    }
}