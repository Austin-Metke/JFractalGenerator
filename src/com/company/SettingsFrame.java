package com.company;

import javax.swing.*;

public class SettingsFrame extends JFrame {

    SettingsFrame() {

        setTitle("Fractal Generator Settings");
        setContentPane(new SettingsPanel());
        setBounds(0, 0, 480, 270);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

}
