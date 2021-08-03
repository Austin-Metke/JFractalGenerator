package com.company;

import java.math.BigDecimal;

/**
 * <code>Complex</code> is a class which implements complex numbers in Java.
 * It includes basic operations that can be performed on complex numbers such as,
 * addition, subtraction, multiplication, conjugate, modulus and squaring.
 *
 * @author Abdul Fatir
 * @version 1.0
 */

public class Complex {
    /**
     * The real, Re(z), part of the <code>Complex</code>.
     */
    private BigDecimal real;
    /**
     * The imaginary, Im(z), part of the <code>Complex</code>.
     */
    private BigDecimal imaginary;

    /**
     * Constructs a new <code>Complex</code> object with both real and imaginary parts 0 (z = 0 + 0i).
     */

    public Complex() {
        real.equals(0.0);
        imaginary.equals(0.0);
    }

    /**
     * Constructs a new <code>Complex</code> object.
     *
     * @param real      the real part, Re(z), of the complex number
     * @param imaginary the imaginary part, Im(z), of the complex number
     */

    public Complex(BigDecimal real, BigDecimal imaginary) {
        this.real = real;
        this.imaginary = imaginary;

    }

    public Complex(BigDecimal real) {
        this.real = real;
    }

    /**
     * Adds another <code>Complex</code> to the current complex number.
     *
     * @param complex_number the complex number to be added to the current complex number
     * @return
     */

    public Complex add(Complex complex_number) {
        BigDecimal a = new BigDecimal(1);
        BigDecimal b = new BigDecimal(1);

        a.equals(this.real.add(complex_number.real));
        b.equals(this.imaginary.add(complex_number.imaginary));

        return new Complex(a, b);

    }

    /**
     * The complex conjugate of the current complex number.
     *
     * @return a <code>Complex</code> object which is the conjugate of the current complex number
     */

/*    public Complex conjugate() {
        return new Complex(this.real, -this.imaginary);
    }*/

    /**
     * The modulus, magnitude or the absolute value of current complex number.
     *
     * @return the magnitude or modulus of current complex number
     */

/*    public double mod() {
        return Math.sqrt(Math.pow(this.real, 2) + Math.pow(this.imaginary, 2));
    }*/

    public BigDecimal abs() {
        //This doesn't do what it should for some reason

        BigDecimal a1 = new BigDecimal(1);

        a1.equals(real.multiply(real).add(imaginary.multiply(imaginary)));

        return a1.abs();

    }


    /**
     * The square of the current complex number.
     *
     * @return a <code>Complex</code> object which is the square of the current complex number
     */

    public Complex square() {


        BigDecimal _real = new BigDecimal(1);
        BigDecimal _imaginary = new BigDecimal(1);

        _real.equals(this.real.multiply(this.real).subtract(this.imaginary.multiply(this.imaginary)));
        _imaginary.equals(this.real.multiply(this.imaginary).multiply(BigDecimal.valueOf(2)));


        return new Complex(_real, _imaginary);
    }

    /**
     * Multiplies another <code>Complex</code> to the current complex number.
     *
     * @param complex_number the complex number to be multiplied to the current complex number
     * @return
     */

/*    public Complex multiply(Complex complex_number) {
        double _real = this.real * complex_number.real - this.imaginary * complex_number.imaginary;
        double _imaginary = this.real * complex_number.imaginary + this.imaginary * complex_number.real;

        this.real = _real;
        this.imaginary = _imaginary;

        return new Complex(_real, _imaginary);
    }

    public Complex sin(Complex c) {

        double real = Math.sin(c.real);
        double imaginary = Math.sin(c.imaginary);

        BigDecimal real;
        BigDecimal imaginary;




        return new Complex(real, imaginary);

    }*/


    /**
     * Prints the complex number in x + yi format
     */

    @Override
    public String toString() {

        return this.real + " + " + this.imaginary + "i";

    }


}