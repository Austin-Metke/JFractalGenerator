package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        int width = 1920;
        int height = 1080;
        Fractal fractal = new Fractal(width, height, Fractal.MANDELBROT, 1000);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame frame = new MainFrame(width, height, fractal);
                frame.setVisible(true);
            }
        });
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SettingsFrame frame = new SettingsFrame(fractal);
                frame.setVisible(true);
            }
        });

        long startTime = System.currentTimeMillis();
        fractal.generateFractalMultiThreaded();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Execution time (Multithreaded): " + executionTime + " milliseconds");

//        startTime = System.currentTimeMillis();
//        fractal.generateFractal();
//        endTime = System.currentTimeMillis();
//        executionTime = endTime - startTime;
//        System.out.println("Execution time (Singlethreaded): " + executionTime + " milliseconds");

    }
}