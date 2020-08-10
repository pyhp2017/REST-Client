package com.HttpRequest;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This is an Image
 * @author Ahmad Foroughi
 * @version 1.0
 */
public class ImagePanel extends JPanel
{
    private BufferedImage image;

    /**
     * Create a new Image Panel
     */
    public ImagePanel()
    {
        try {
            image = ImageIO.read(new File("temp.png"));
        } catch (IOException ex) {
            System.out.println("File Not Found");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image,0,0,this);
    }
}
