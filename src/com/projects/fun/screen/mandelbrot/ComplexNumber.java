package com.projects.fun.screen.mandelbrot;

import java.math.BigDecimal;

public class ComplexNumber {

  private static final int I_SQUARED = -1;
  private double real;
  private double imaginary;

  public ComplexNumber(double real, double imaginary) {
    this.real = real;
    this.imaginary = imaginary;
  }

  public ComplexNumber squared() {
    double firstInEach = real * real;
    double lastInEach = imaginary * imaginary * I_SQUARED;

    double realPart = firstInEach + lastInEach;

    double imaginaryPart = 2 * real * imaginary;

    return new ComplexNumber(realPart, imaginaryPart);
  }

  public double size() {
    return Math.sqrt((real * real) + (imaginary * imaginary));
  }

  public double getReal() {
    return real;
  }

  public double getImaginary() {
    return imaginary;
  }

  public ComplexNumber add(ComplexNumber c) {
    return new ComplexNumber(real + c.getReal(), imaginary + c.getImaginary());
  }
}
