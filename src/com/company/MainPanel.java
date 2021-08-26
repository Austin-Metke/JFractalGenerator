package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class MainPanel extends JPanel implements MouseListener {

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

    @Override
    public void mouseClicked(MouseEvent e) {

        System.out.println("Click!");


    }

    @Override
    public void mousePressed(MouseEvent e) {

        int mouseX = e.getX();
        int mouseY = e.getY();




    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
