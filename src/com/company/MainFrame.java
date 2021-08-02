package com.company;

import javax.swing.*;

public class MainFrame extends JFrame {

    MainFrame() {

        setTitle("Fractal Generator");
        setContentPane(new MainPanel());
        setBounds(0, 0, 1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

}
