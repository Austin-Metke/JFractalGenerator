package com.company;

import javax.swing.*;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
public class Main {

    public static void main(String[] args) {
        // Get the local graphics environment
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        // Get the default screen device
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();

        // Get the current display mode (resolution) of the default screen device
        DisplayMode mode = defaultScreen.getDisplayMode();

        // Extract the width and height from the DisplayMode
        int width = 3840;
        int height = 2160;

        Fractal fractal = new Fractal(width, height, Fractal.MANDELBROT, 100);
        SwingUtilities.invokeLater(() -> new MainFrame(width, height, fractal));


        SwingUtilities.invokeLater(() -> new SettingsFrame(fractal));


//        long startTime = System.currentTimeMillis();
//        fractal.generateFractalMultiThreaded();
//        long endTime = System.currentTimeMillis();
//        long executionTime = endTime - startTime;
//        System.out.println("Execution time (Multithreaded): " + executionTime + " milliseconds");
//
//        startTime = System.currentTimeMillis();
//        fractal.generateFractal();
//        endTime = System.currentTimeMillis();
//        executionTime = endTime - startTime;
//        System.out.println("Execution time (Singlethreaded): " + executionTime + " milliseconds");


        long startTime = System.currentTimeMillis();
        fractal.generateFractalMultiThreadedRefactored();
        //fractal.generateFractalMultiThreadedRefactored();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Execution time (Multithreaded (Refactored)): " + executionTime + " milliseconds");

    }
}