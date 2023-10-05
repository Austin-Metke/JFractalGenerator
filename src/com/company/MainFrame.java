package com.company;

import javax.swing.*;

public class MainFrame extends JFrame {

    /**
     * Creates a window to display the fractal
     */

    MainFrame(int width, int height, Fractal fractal) {
        setContentPane(fractal); //Creates a window with the set settings in MainPanel() in the MainPanel class
        setBounds(0, 0, width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exists the program upon window closure
        setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

}
