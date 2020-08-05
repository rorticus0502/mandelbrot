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

    mandelbrotApplication.addMouseListener(new ZoomController(mandelbrotApplication));

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

    if (orbit == MAX_ITERATIONS) {
      return BLACK;
    }

    double colorFactor = 254d * (Math.sin(Integer.valueOf(orbit).doubleValue() / 50d));

    if (colorFactor > 0d) {

      int redPart = Double.valueOf(colorFactor).intValue();
      int greenPart = Double.valueOf(colorFactor / 2).intValue();
      int bluePart = 0;

      return new Color(redPart, greenPart, bluePart);
    }

    colorFactor = Math.abs(colorFactor);

    int bluePart = Double.valueOf(colorFactor).intValue();
    int greenPart = Double.valueOf(colorFactor / 4).intValue();
    int redPart = 0;

    return new Color(redPart, greenPart, bluePart);



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

  public void updateRegion(double realMin, double realMax, double imaginaryMin, double imaginaryMax) {
    this.realMin = realMin;
    this.realMax = realMax;
    this.imaginaryMin = imaginaryMin;
    this.imaginaryMax = imaginaryMax;

    repaint();
  }

  public double convertXPositionToRealValue(int selectedX) {

    double screenRatio =
        Integer.valueOf(selectedX).doubleValue() / Integer.valueOf(screenWidth).doubleValue();

    double realUnadjustedForOrigin = screenRatio * (realMax - realMin);

    return realMin + realUnadjustedForOrigin;
  }

  public double convertYPositionToImaginaryValue(int selectedY) {

    double screenRatio =
        Integer.valueOf(screenHeight - selectedY).doubleValue() / Integer.valueOf(screenHeight)
            .doubleValue();

    double imaginaryUnadjustedForOrigin = screenRatio * (imaginaryMax - imaginaryMin);

    return imaginaryMin + imaginaryUnadjustedForOrigin;
  }

}
