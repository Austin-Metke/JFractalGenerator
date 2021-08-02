package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class MainPanel extends JPanel {

    static BufferedImage buffimg = new BufferedImage((int)Main.WIDTH, (int)Main.HEIGHT, 1);

    MainPanel() {

        setBounds(0, 0, 1920, 1080);
        setVisible(true);
        setLayout(null);

    }

    public void paintComponent(Graphics g) {
        g.drawImage(buffimg, 0, 0, (ImageObserver)null);
        this.repaint();
        g.dispose();
    }

}
