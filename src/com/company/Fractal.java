package com.company;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;

public class Fractal {

    // Pointers to generate types of sets
    public static final int GENERATE_MANDELBROT = 0;
    public static final int GENERATE_JULIA = 1;
    public static final int GENERATE_CUSTOM = 2;

    // Start and end of the coordinate plane
    double RESTART = -2.5;
    double REEND = 1;
    double IMSTART = -1;
    double IMEND = 1;
    private int[][] iterationsArr;
    private int startx;
    private int starty;
    private double height;
    private double width;
    private double real;
    private double imaginary;
    private int FRACTAL_TYPE;

    // Default max iterations
    int ITERATIONS = 255;



    private int ComputeIterations(Complex c, int FRACTAL_TYPE) {

        int iterationCounter = 0;
        Complex z = c;

        switch (FRACTAL_TYPE) {

            case 0:
                // Computes iterations for Mandelbrot set


                //Recursive mandelbrot function, f_c(z) = z^2 + c
                while (Math.pow(z.real, 2) + Math.pow(z.imaginary, 2) < 4 && iterationCounter < ITERATIONS) {

                    z = z.square().add(c);
                    iterationCounter += 1;

                }
            case 1:
                // Computes iterations for Julia set

                // Default number for Julia set

                real = -0.4;

                imaginary = -0.58;

                c = new Complex(real, imaginary);

                while (Math.pow(z.real, 2) + Math.pow(z.imaginary, 2) < 4 && iterationCounter < ITERATIONS) {

                    z = z.square().add(c);
                    iterationCounter += 1;
                }

            case 2:
                // Computes iterations for a custom fractal
                // Will add later

        }

        return iterationCounter;
    }

    public void FractalSettings(double RESTART, double REEND, double IMSTART, double IMEND, double width, double height,
                                int startx, int starty, int[][] iterationsArr, int FRACTAL_TYPE, int ITERATIONS) {

        this.RESTART = RESTART;
        this.REEND = REEND;
        this.IMSTART = IMSTART;
        this.IMEND = IMEND;
        this.iterationsArr = iterationsArr;
        this.FRACTAL_TYPE = FRACTAL_TYPE;
        this.width = width;
        this.height = height;
        this.startx = startx;
        this.starty = starty;
        this.ITERATIONS = ITERATIONS;

    }

    void Fractal() {

        float hue = 0;
        float brightness;

        //Nested for loop to traverse each pixel on screen
        for (int x = startx; (double) x < width; x++) {
            for (int y = starty; (double) y < height; y++) {

                real = RESTART + (double) x / width * (REEND - RESTART);
                imaginary = IMSTART + (double) y / height * (IMEND - IMSTART);

                Complex c = new Complex(real, imaginary);


                iterationsArr[x][y] = ComputeIterations(c, FRACTAL_TYPE); //Sets amount of iterations to determine whether the pixel at x,y is part of the mandelbrot set or not



                brightness = 0.0f; //If pixel is part of the set, it's brightness is 0 (black)

                //If amount of iterations for pixel at x,y is less than max iterations, it's given a brightness of 1 and a color
                if (this.iterationsArr[x][y] < ITERATIONS) {
                    hue = 0.255f * this.iterationsArr[x][y] / ITERATIONS;   //Color of pixel is determined by amount of iterations per pixel / max amount of iterations

                    brightness = 1.0f + this.iterationsArr[x][y];
                }

                MainPanel.buffimg.setRGB(x, y, Color.HSBtoRGB(hue,1.0f , brightness)); //pixel at x,y is set

            }

        }

        File file = new File("fractal.png");

        try {
            ImageIO.write(MainPanel.buffimg, "png", file);
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

}