package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class MainPanel extends JPanel {
    
    static BufferedImage buffimg = new BufferedImage((int)Main.WIDTH, (int)Main.HEIGHT, 1);
    
    /**
     * MainPanel() is the constructor for the main panel;
     */
    MainPanel() {

        setBounds(0, 0, 1920, 1080); //center of screen, 1920x1080 dimensions
        setVisible(true);
        setLayout(null);  //No desired layout, hence null

    }
    /**
     * paintComponent is the method that draws the fractal
     */
    public void paintComponent(Graphics g) {
        g.drawImage(buffimg, 0, 0, (ImageObserver)null);
        this.repaint();
        g.dispose();
    }

}
