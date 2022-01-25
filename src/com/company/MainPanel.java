package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainPanel extends JPanel {

    static BufferedImage buffimg = new BufferedImage((int)Main.WIDTH, (int)Main.HEIGHT, 1);

    /**
     * MainPanel() is the constructor for the main frame
     */
    MainPanel() {

        setBounds(0, 0, (int) Main.WIDTH, (int) Main.HEIGHT); //Opens at the center of screen with 1920x1080 dimensions
        setVisible(true);
        setLayout(null);  //No desired layout, hence null

    }
    /**
     * paintComponent(Graphics g) is the method that draws the fractal
     */

    public void paintComponent(Graphics g) {
        g.drawImage(buffimg, 0, 0, null);
        this.repaint();
        g.dispose();
    }
}
