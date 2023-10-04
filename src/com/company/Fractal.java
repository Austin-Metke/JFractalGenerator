package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Fractal extends JPanel {
    private boolean isDone;

    public static final int MANDELBROT = 0;
    public static final int JULIA = 1;

    private final BufferedImage buffimg;

    private FractalBounds fractalBounds; // Start and end of the coordinate plane

    private final int[][] iterationsArr;
    private final double height;
    private final double width;
    private int mouseX;
    private int mouseY;
    private double real;
    private double imaginary;


    private int FRACTAL_TYPE;
    private double zoom;
    private double xCenter;
    private double yCenter;

    public void setC(Complex c) {
        C = c;
    }

    private Complex C;

    int ITERATIONS;

    private int computeIterations(Complex c) {

        int iterationCounter = 0;
        Complex z = c;
        switch (this.FRACTAL_TYPE) {
            case MANDELBROT:
                // Computes iterations for Mandelbrot set

                //Recursive mandelbrot function, f_c(z) = z^2 + c
                while (z.abs() < 4 && iterationCounter < ITERATIONS) {
                    z = z.square().add(c);
                    iterationCounter += 1;
                }
                break;
            case JULIA:

                if (C == null) {
                    real = 0;
                    imaginary = 0;
                } else {
                    c = C;
                }

                while (z.abs() < 4 && iterationCounter < ITERATIONS) {
                    z = z.square().add(c);
                    iterationCounter += 1;
                }
                break;
            case 2:
                // Computes iterations for a custom fractal
                // Will add later

        }
        return iterationCounter;
    }

    public int getFRACTAL_TYPE() {
        return FRACTAL_TYPE;
    }

    public void setfractalType(int FRACTAL_TYPE) {
        this.FRACTAL_TYPE = FRACTAL_TYPE;
    }

    public void generateFractalMultiThreaded() {

        System.out.println("MULTITHREADED");
        this.isDone = false;
        this.fractalBounds = new FractalBounds(
                xCenter - (width / 2.0) / (0.5 * zoom * width),
                xCenter + (width / 2.0) / (0.5 * zoom * width),
                yCenter - (height / 2.0) / (0.5 * zoom * height),
                yCenter + (height / 2.0) / (0.5 * zoom * height)
        );

        double RESTART = this.fractalBounds.RESTART;
        double REEND = this.fractalBounds.REEND;
        double IMSTART = this.fractalBounds.IMSTART;
        double IMEND = this.fractalBounds.IMEND;

        int numThreads = Runtime.getRuntime().availableProcessors(); // Get the number of available CPU cores
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int threadId = 0; threadId < numThreads; threadId++) {
            final int finalThreadId = threadId;
            executor.submit(() -> {
                int startX = (int) (finalThreadId * width / numThreads);
                int endX = (int) ((finalThreadId + 1) * width / numThreads);

                for (int x = startX; x < endX; x++) {
                    for (int y = 0; y < height; y++) {
                        float hue = 0;
                        final float brightness;
                        float brightness1;

                        real = RESTART + ((x / width)) * (REEND - RESTART) / (this.zoom);
                        imaginary = IMSTART + ((y / height)) * (IMEND - IMSTART) / (this.zoom);

                        Complex c = new Complex(real, imaginary);

                        iterationsArr[x][y] = computeIterations(c); //Sets amount of iterations to determine whether the pixel at x,y is part of the mandelbrot set or not

                        brightness1 = 0.0f; //If pixel is part of the set, it's brightness is 0 (black)

                        //If amount of iterations for pixel at x,y is less than max iterations, it's given a brightness of 1 and a color
                        if (this.iterationsArr[x][y] < ITERATIONS) {
                            hue = 0.255f * this.iterationsArr[x][y] / ITERATIONS;   //Color of pixel is determined by amount of iterations per pixel / max amount of iterations

                            brightness1 = 1.0f + this.iterationsArr[x][y];
                        }

                        brightness = brightness1;
                        this.buffimg.setRGB(x, y, Color.HSBtoRGB(hue, 1.0f, brightness)); //pixel at x,y is set
                    }
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Save the image and set isDone to true
        // After all computation is done, schedule a repaint on the EDT
        SwingUtilities.invokeLater(() -> {
            repaint();
            this.isDone = true;
        });
        File file = new File("fractal.png");

        try {
            ImageIO.write(this.buffimg, "png", file);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void generateFractal() {

        float hue = 0;
        float brightness;
        this.isDone = false;

        this.fractalBounds = new FractalBounds(
                xCenter - (width / 2.0) / (0.5 * zoom * width),
                xCenter + (width / 2.0) / (0.5 * zoom * width),
                yCenter - (height / 2.0) / (0.5 * zoom * height),
                yCenter + (height / 2.0) / (0.5 * zoom * height)
        );

        double RESTART = this.fractalBounds.RESTART;
        double REEND = this.fractalBounds.REEND;
        double IMSTART = this.fractalBounds.IMSTART;
        double IMEND = this.fractalBounds.IMEND;

        //Nested for loop to traverse each pixel on screen
        for (int x = 0; (double) x < width; x++) {
            for (int y = 0; (double) y < height; y++) {
                real = RESTART + ((x / width)) * (REEND - RESTART) / (this.zoom);
                imaginary = IMSTART + ((y / height)) * (IMEND - IMSTART) / (this.zoom);
                Complex c = new Complex(real, imaginary);

                iterationsArr[x][y] = computeIterations(c); //Sets amount of iterations to determine whether the pixel at x,y is part of the mandelbrot set or not

                brightness = 0.0f; //If pixel is part of the set, it's brightness is 0 (black)

                //If amount of iterations for pixel at x,y is less than max iterations, it's given a brightness of 1 and a color
                if (this.iterationsArr[x][y] < ITERATIONS) {
                    hue = 0.255f * this.iterationsArr[x][y] / ITERATIONS;   //Color of pixel is determined by amount of iterations per pixel / max amount of iterations

                    brightness = 1.0f + this.iterationsArr[x][y];
                }

                this.buffimg.setRGB(x, y, Color.HSBtoRGB(hue, 1.0f, brightness)); //pixel at x,y is set
                repaint();
            }
        }
        this.isDone = true;
        File file = new File("fractal.png");

        try {
            ImageIO.write(this.buffimg, "png", file);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public Fractal(int width, int height) {
        this.isDone = true;
        this.xCenter = -0.5;
        this.yCenter = 0;
        this.zoom = 1;
        this.ITERATIONS = 255;
        this.fractalBounds = new FractalBounds();
        this.FRACTAL_TYPE = JULIA;
        this.width = width;
        this.height = height;
        this.iterationsArr = new int[width][height];
        this.buffimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        setBounds(0, 0, width, height);
        setVisible(true);
        setLayout(null);  //No desired layout, hence null
        addMouseWheelListener(e -> {
            this.mouseX = e.getX();
            this.mouseY = e.getY();
            this.zoom = zoom * (e.getWheelRotation() > 0 ? 0.9 : 1.1);
            this.xCenter = xCenter + (mouseX - this.width / 2.0) / (0.5 * zoom * this.width);
            this.yCenter = yCenter + (mouseY - this.height / 2.0) / (0.5 * zoom * this.height);

        });
    }

    public Fractal(int width, int height, FractalBounds fractalBounds, int FRACTAL_TYPE, double zoom, int ITERATIONS) {
        this(width, height);
        this.ITERATIONS = ITERATIONS;
        this.isDone = false;
        this.zoom = zoom;
        this.fractalBounds = fractalBounds;
        this.FRACTAL_TYPE = FRACTAL_TYPE;
    }

    public Fractal(int width, int height, int FRACTAL_TYPE) {
        this(width, height);
        this.FRACTAL_TYPE = FRACTAL_TYPE;

    }

    public Fractal(int width, int height, int FRACTAL_TYPE, int ITERATIONS) {
        this(width, height, FRACTAL_TYPE);
        this.ITERATIONS = ITERATIONS;
    }

    public Fractal(int width, int height, int FRACTAL_TYPE, Complex C, int ITERATIONS) {
        this(width, height, FRACTAL_TYPE, ITERATIONS);
        this.C = C;
    }

    @Override
    public void paintComponent(Graphics g) {
        if (!this.isDone) {
            g.drawImage(buffimg, 0, 0, null);
            this.repaint();
        }

    }

    public boolean isDone() {
        return this.isDone;
    }

    static class FractalBounds {

        double RESTART;
        double REEND;
        double IMSTART;
        double IMEND;

        FractalBounds() {
            this.RESTART = -2.5;
            this.REEND = 1;
            this.IMSTART = -1;
            this.IMEND = 1;
        }

        FractalBounds(double RESTART, double REEND, double IMSTART, double IMEND) {
            this.RESTART = RESTART;
            this.REEND = REEND;
            this.IMSTART = IMSTART;
            this.IMEND = IMEND;
        }

    }
}