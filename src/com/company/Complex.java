package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Complex {

    public double real;
    public double imaginary;

    public Complex(double real, double imaginary) {

        this.real = real;
        this.imaginary = imaginary;

    }

    public Complex() {
        this.real = 0;
        this.imaginary = 0;
    }

    public double abs() {
        return Math.sqrt(this.real*this.real + this.imaginary*this.imaginary);
    }

    public double magnitudeSquared() {
        return this.real*this.real + this.imaginary*this.imaginary;
    }

    public Complex square() {
        return new Complex(Math.pow(this.real, 2) - Math.pow(this.imaginary, 2), 2*this.real*this.imaginary);
    }

    public Complex add(Complex z) {
        this.real = this.real + z.real;
        this.imaginary = this.imaginary + z.imaginary;
        return this;
    }

    public Complex conjugate() {
        return new Complex(this.real, this.imaginary*-1);
    }


    public String toString()
    {
        String re = String.valueOf(this.real);
        String im;
        if(this.imaginary == 0)
            im = this.imaginary+"i";
        else
            im = "+"+this.imaginary+"i";
        return re+im;
    }

    public static Complex parse(String s) {
        String complexNumberPattern = "([-+]?\\d*\\.?\\d*)([-+]\\d*\\.?\\d*)i?";
        Pattern pattern = Pattern.compile(complexNumberPattern);
        Matcher matcher = pattern.matcher(s);
        if(!matcher.matches()) return null;
        double real = Double.parseDouble(matcher.group(1));
        double imag = Double.parseDouble(matcher.group(2));

        return new Complex(real, imag);
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof Complex complex)) return false;
        return this.real == complex.real && this.imaginary == complex.imaginary;
    }

}