package com.company;

import com.company.MainPanel;

import java.awt.*;
import java.math.BigInteger;
import java.math.RoundingMode;
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

    public Fractal() {

    }

    public int ComputeIterations(Complex c, int FRACTAL_TYPE) {

        int iterationCounter = 0;
        Complex z = c;

        switch (FRACTAL_TYPE) {

            case 0:
                // Computes iterations for Mandelbrot set

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

                // Grabs a complex number from juliaComplex() text field if one is present

                /*
                 * if(!juliaTextField.getText().isEmpty()) {
                 *
                 * String[] complex;
                 *
                 * complex = juliaComplex.getText().split(", );
                 *
                 * c = new Complex(double.parsedouble(complex[0].stripTrailing()), double
                 * parsedouble(complex[1].stripTrailing()));
                 *
                 *
                 * }
                 */

            case 2:
                // Computes iterations for a custom fractal
                // Will add later

        }

        return iterationCounter;
    }

    public void GenerateFractal(double RESTART, double REEND, double IMSTART, double IMEND, double width, double height,
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

        for (int x = startx; (double) x < width; x++) {
            for (int y = starty; (double) y < height; y++) {

                real = RESTART + (double) x / width * (REEND - RESTART);
                imaginary = IMSTART + (double) y / height * (IMEND - IMSTART);

                Complex c = new Complex(real, imaginary);

                iterationsArr[x][y] = ComputeIterations(c, FRACTAL_TYPE);

                float hue = 0.255f * this.iterationsArr[x][y] / ITERATIONS;

                float brightness = 0.0f;

                if (this.iterationsArr[x][y] < ITERATIONS) {
                    brightness = 1.0f + this.iterationsArr[x][y];
                }

                MainPanel.buffimg.setRGB(x, y, Color.HSBtoRGB(hue, SettingsPanel.Saturation, brightness));

            }

        }

        File file = new File("fractal.png");

        try {
            ImageIO.write(MainPanel.buffimg, "png", file);
        } catch (Exception ignored) {

        }

    }

}