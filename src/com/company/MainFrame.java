package com.company;

import javax.swing.*;

public class MainFrame extends JFrame {

    /**
     * Creates a window to display the fractal
     */
    MainFrame() {

        setTitle("Fractal Generator");
        setContentPane(new MainPanel()); //Creates a window with the set settings in MainPanel() in the MainPanel class
        setBounds(0, 0, (int) Main.WIDTH, (int) Main.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exists the program upon window closure
        setVisible(true);

    }

}
