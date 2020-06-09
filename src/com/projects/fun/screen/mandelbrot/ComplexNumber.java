package com.projects.fun.screen.mandelbrot;

public class ComplexNumber {

  private static final int I_SQUARED = -1;
  private int real;
  private int imaginary;

  public ComplexNumber(int real, int imaginary) {
    this.real = real;
    this.imaginary = imaginary;
  }

  public ComplexNumber squared() {
    int firstInEach = real * real;
    int lastInEach = imaginary * imaginary * I_SQUARED;

    int realPart = firstInEach + lastInEach;

    int imaginaryPart = 2 * real * imaginary;

    return new ComplexNumber(realPart, imaginaryPart);
  }

  public int getReal() {
    return real;
  }

  public int getImaginary() {
    return imaginary;
  }
}
