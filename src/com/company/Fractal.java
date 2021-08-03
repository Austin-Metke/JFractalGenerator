package com.company;

import java.awt.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;


public class Fractal {

    //Pointers to generate types of sets
    public static final int GENERATE_MANDELBROT = 0;
    public static final int GENERATE_JULIA = 1;
    public static final int GENERATE_CUSTOM = 2;

    //Start and end of the coordinate plane
    BigDecimal RESTART = BigDecimal.valueOf(-2);
    BigDecimal REEND = BigDecimal.valueOf(1);
    BigDecimal IMSTART = BigDecimal.valueOf(-1);
    BigDecimal IMEND = BigDecimal.valueOf(1);
    private int[][] iterationsArr;
    private int startx;
    private int starty;
    private double height;
    private double width;
    private int FRACTAL_TYPE;

    //Default max iterations
    int ITERATIONS = 255;

    public Fractal() {

    }

    public int ComputeIterations(Complex c, int FRACTAL_TYPE) {

        int iterationCounter = 0;
        Complex z = c;

        switch (FRACTAL_TYPE) {

            case 0:
                //Computes iterations for Mandelbrot set

                while (z.abs().compareTo(BigDecimal.valueOf(2.0)) < 2.0 && iterationCounter < ITERATIONS) {

                    z = z.square().add(c);
                    iterationCounter += 1;

                }
            case 1:
                //Computes iterations for Julia set

                //Default number for Julia set

                BigDecimal r = new BigDecimal(-0.4);


                BigDecimal i = new BigDecimal(-0.58);

                c = new Complex(r, i);

                while (z.abs().compareTo(BigDecimal.valueOf(2.0)) < 2.0 && iterationCounter < ITERATIONS) {

                    z = z.square().add(c);
                    iterationCounter += 1;
                }


                //Grabs a complex number from juliaComplex() text field if one is present

                /*if(!juliaTextField.getText().isEmpty()) {

                String[] complex;

                complex = juliaComplex.getText().split(", );

                c = new Complex(Double.parseDouble(complex[0].stripTrailing()), Double parseDouble(complex[1].stripTrailing()));


                }*/

            case 2:
                //Computes iterations for a custom fractal
                //Will add later

        }
        return iterationCounter;
    }

    public void GenerateFractal(BigDecimal RESTART, BigDecimal REEND, BigDecimal IMSTART, BigDecimal IMEND, double width, double height, int startx, int starty, int[][] iterationsArr, int FRACTAL_TYPE, int ITERATIONS) {

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

                BigDecimal real = new BigDecimal(1);
                BigDecimal imaginary = new BigDecimal(1);


                real.equals(RESTART.add(BigDecimal.valueOf(x)).divide(BigDecimal.valueOf(width), 2, RoundingMode.HALF_UP).multiply(REEND.subtract(RESTART)));
                imaginary.equals(IMSTART.add(BigDecimal.valueOf(y)).divide(BigDecimal.valueOf(height), 2, RoundingMode.HALF_UP).multiply(IMEND.subtract(IMSTART)));

                Complex c = new Complex(real, imaginary);

                iterationsArr[x][y] = ComputeIterations(c, FRACTAL_TYPE);
                float hue = 0.255f * (float) iterationsArr[x][y] / (float) ITERATIONS;
                float brightness = 0.0f;

                if (this.iterationsArr[x][y] < ITERATIONS) {
                    brightness = 1.0f;
                }

                MainPanel.buffimg.setRGB(x, y, Color.HSBtoRGB(hue, SettingsPanel.Saturation, brightness));

            }

        }

    }

}