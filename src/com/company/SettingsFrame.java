package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsFrame extends JFrame {
    private JButton generateButton;
    private JRadioButton mandelbrotRadioButton;
    private JRadioButton juliaRadioButton;
    private JTextField juliaTextField;
    private JLabel zoomLabel;
    private JSlider zoomSlider;
    private Fractal fractal;

    public SettingsFrame(Fractal fractal) {
        this.fractal = fractal;
        initializeUI();
    }

    private void initializeUI() {
        // Set the frame properties
        setTitle("Fractal Settings");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);
        // Create a panel for the radio buttons
        JPanel radioPanel = new JPanel();
        mandelbrotRadioButton = new JRadioButton("Mandelbrot");
        juliaRadioButton = new JRadioButton("Julia");
        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(mandelbrotRadioButton);
        radioButtonGroup.add(juliaRadioButton);
        radioPanel.add(mandelbrotRadioButton);
        radioPanel.add(juliaRadioButton);

        // Create a panel for the Julia input text field
        JPanel juliaPanel = new JPanel();
        juliaTextField = new JTextField(10);
        juliaTextField.setEnabled(false); // Initially disabled
        juliaPanel.add(new JLabel("Julia Set Constant:"));
        juliaPanel.add(juliaTextField);

        // Create the generate button
        generateButton = new JButton("Generate Fractal");

        generateButton.addActionListener(e -> {

            if (juliaRadioButton.isSelected()) {
                String complex = juliaTextField.getText();
                fractal.setfractalType(Fractal.JULIA);
                Complex C = Complex.parse(complex);
                fractal.setC(C);

            } else if(mandelbrotRadioButton.isSelected()) {
                fractal.setfractalType(Fractal.MANDELBROT);
            }

            fractal.generateFractalMultiThreadedRefactored();
        });

        // Add components to the frame
        add(radioPanel, BorderLayout.NORTH);
        add(juliaPanel, BorderLayout.CENTER);
        add(generateButton, BorderLayout.SOUTH);

        // Add an ActionListener to the Julia radio button to enable/disable the text field
        juliaRadioButton.addActionListener(e -> juliaTextField.setEnabled(true));

        mandelbrotRadioButton.addActionListener(e -> juliaTextField.setEnabled(false));


        // Create the zoom controls
        JPanel zoomPanel = new JPanel();
        zoomLabel = new JLabel("Zoom Level: 1.0");
        zoomSlider = new JSlider(10, 100, 100); // Values represent zoom levels from 0.1 to 10.0
        zoomSlider.addChangeListener(e -> {
            double zoomValue = zoomSlider.getValue() / 10.0;
            zoomLabel.setText("Zoom Level: " + zoomValue);
        });
        zoomPanel.add(zoomLabel);
        zoomPanel.add(zoomSlider);
        zoomPanel.setVisible(true);
        this.add(zoomPanel);
        this.setLayout(new FlowLayout());
    }
}
