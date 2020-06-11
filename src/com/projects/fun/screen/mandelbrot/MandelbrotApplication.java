package com.projects.fun.screen.mandelbrot;

import static java.awt.Color.BLACK;
import static java.awt.Color.BLUE;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;

public class MandelbrotApplication extends JFrame {

  private static Map<Integer, Color> colorMap = new HashMap<>();

  public static void main(String[] args) {

    MandelbrotApplication mandelbrotApplication = new MandelbrotApplication();

    mandelbrotApplication.setDefaultCloseOperation(EXIT_ON_CLOSE);
    mandelbrotApplication.setSize(800, 800);

    mandelbrotApplication.setVisible(true);
  }

  @Override
  public void paint(Graphics graphics) {

    for (int w = 0; w < 800; w++) {
      for (int h = 0; h < 800; h++) {

        double real = convertWidthToReal(w);
        double imaginary = convertHeightToImaginary(h);
        ComplexNumber c = new ComplexNumber(real, imaginary);

        Color color = generateColor(escapeVelocity(c));

        graphics.setColor(color);

        graphics.fillRect(w, h, 1, 1);

      }
    }
    System.out.println("Done");

  }

  private Color generateColor(int orbit) {

    int randomColorKey = Double.valueOf(Math.random() * Integer.MAX_VALUE).intValue();

    return colorMap.computeIfAbsent(orbit, (key) -> new Color(randomColorKey));
  }

  private int escapeVelocity(ComplexNumber c) {


    int counter = 0;

    double currentSize = 0d;
    ComplexNumber zN = new ComplexNumber(0d, 0d);

    while (currentSize < 100d && counter < 10000) {

      counter++;
      ComplexNumber zNPlusOne = zN.squared().add(c);
      currentSize = zNPlusOne.size();
      zN = zNPlusOne;
    }

    return counter;

  }

  private double convertWidthToReal(int width) {
    return (Integer.valueOf(width).doubleValue() / 200.0d) - 2.0d;
  }

  private double convertHeightToImaginary(int height) {

    int verticalPosition = 800 - height;
    return (Integer.valueOf(verticalPosition).doubleValue() / 200.0d) - 2.0d;
  }

}
