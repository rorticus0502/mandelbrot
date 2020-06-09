package com.projects.fun.screen.mandelbrot;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ComplexNumberTest {

  @Test
  public void testSquare() {

    ComplexNumber twoPlusThreeI = new ComplexNumber(2, 3);

    ComplexNumber twoPlusThreeISquared = twoPlusThreeI.squared();

    assertEquals(-5, twoPlusThreeISquared.getReal());
    assertEquals(12, twoPlusThreeISquared.getImaginary());

    ComplexNumber threeMinusThreeI = new ComplexNumber(3, -3);

    ComplexNumber threeMinusThreeISquared = threeMinusThreeI.squared();

    assertEquals(0, threeMinusThreeISquared.getReal());
    assertEquals(-18, threeMinusThreeISquared.getImaginary());

    ComplexNumber oneMinusFourI = new ComplexNumber(1, -4);

    ComplexNumber oneMinusFourISquared = oneMinusFourI.squared();

    assertEquals(-15, oneMinusFourISquared.getReal());
    assertEquals(-8, oneMinusFourISquared.getImaginary());

    ComplexNumber i = new ComplexNumber(0, 1);

    ComplexNumber iSquared = i.squared();

    assertEquals(-1, iSquared.getReal());
    assertEquals(0, iSquared.getImaginary());







  }

}