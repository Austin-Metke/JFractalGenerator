package com.company;

class Complex {

    public double real;
    public double imaginary;

    public Complex(double real, double imaginary) {

        this.real = real;
        this.imaginary = imaginary;


    }

    public Complex square() {


        double _real = this.real * this.real - this.imaginary * this.imaginary;
        double _imaginary = 2 * this.real * this.imaginary;

        return new Complex(_real, _imaginary);

    }

    public Complex add(Complex z) {

        this.real = this.real + z.real;
        this.imaginary = this.imaginary + z.imaginary;

        return this;


    }

    public String toString()
    {
        String re = this.real+"";
        String im = "";
        if(this.imaginary < 0)
            im = this.imaginary+"i";
        else
            im = "+"+this.imaginary+"i";
        return re+im;
    }


}