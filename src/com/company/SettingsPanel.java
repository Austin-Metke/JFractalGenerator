package com.company;

import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel extends JPanel {

    JButton generateFractalButton = new JButton();
    public JSlider changeHue = new JSlider();
    JSlider changeSaturation = new JSlider();
    JButton undoButton = new JButton();
    JButton redoButton = new JButton();

    static float colorSlider = 1.0F;
    static float Saturation = 1.0F;


    SettingsPanel() {

        setBounds(0, 0, 480, 270);





        generateFractalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {





            }
        });

    }



}
