package com.projects.fun.screen.mandelbrot;

import static java.awt.Color.BLACK;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;

public class MandelbrotApplication extends JFrame {

  private static Map<Integer, Color> colorMap = new HashMap<>();
  private static final int MAX_ITERATIONS = 10000;
  private static final double DIVERGENCE_THRESHOLD = 10000000d;

  private int screenWidth = 1200;
  private int screenHeight = 900;

  private double realMin = -2.0d;
  private double realMax = 1.0d;
  private double imaginaryMin = -1.5d;
  private double imaginaryMax = 1.5d;

  public static void main(String[] args) {

    colorMap.put(MAX_ITERATIONS, BLACK);

    MandelbrotApplication mandelbrotApplication = new MandelbrotApplication();

    mandelbrotApplication.setDefaultCloseOperation(EXIT_ON_CLOSE);
    mandelbrotApplication.setSize(mandelbrotApplication.screenWidth, mandelbrotApplication.screenHeight);

    mandelbrotApplication.setVisible(true);
  }

  @Override
  public void paint(Graphics graphics) {

    for (int x = 0; x < screenWidth; x++) {
      for (int h = 0; h < screenHeight; h++) {

        double real = convertWidthToReal(x);
        double imaginary = convertHeightToImaginary(h);
        ComplexNumber c = new ComplexNumber(real, imaginary);

        Color color = generateColor(escapeVelocity(c));

        graphics.setColor(color);

        graphics.fillRect(x, h, 1, 1);

      }
    }
  }

  private Color generateColor(int orbit) {

    int randomColorKey = Double.valueOf(Math.random() * Integer.MAX_VALUE).intValue();
    return colorMap.computeIfAbsent(orbit, (key) -> new Color(randomColorKey));
  }

  private int escapeVelocity(ComplexNumber c) {

    int counter = 0;

    ComplexNumber zN = new ComplexNumber(0d, 0d);

    while (zN.size() < DIVERGENCE_THRESHOLD && counter < MAX_ITERATIONS) {

      counter++;
      zN = zN.squared().add(c);
    }

    return counter;
  }

  private double convertWidthToReal(int xPosition) {
    return (Integer.valueOf(xPosition).doubleValue() / calculateRealNumerator()) + realMin;
  }

  private double calculateRealNumerator() {
    return Integer.valueOf(screenWidth).doubleValue() / (realMax - realMin);
  }

  private double convertHeightToImaginary(int depthOnScreen) {

    int yPosition = screenHeight - depthOnScreen;
    return (Integer.valueOf(yPosition).doubleValue() / calculateImaginaryNumerator()) + imaginaryMin;
  }

  private double calculateImaginaryNumerator() {
    return Integer.valueOf(screenHeight).doubleValue() / (imaginaryMax - imaginaryMin);
  }

}
