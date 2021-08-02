package com.company;

public class Main {

    //Width and Height of fractal image

    public static double WIDTH = 1920;
    public static double HEIGHT = 1080;




    public static void main(String[] args) {

        new MainFrame();
        new SettingsFrame();


        int[][] iterationsArr = new int[(int)WIDTH][(int)HEIGHT];

        Fractal fractal = new Fractal();

        fractal.GenerateFractal(fractal.RESTART, fractal.REEND, fractal.IMSTART, fractal.IMEND, WIDTH, HEIGHT, 0, 0, iterationsArr, Fractal.GENERATE_JULIA, fractal.ITERATIONS);
        fractal.Fractal();

    }
}
