package com.projects.fun.screen.mandelbrot;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ZoomController extends MouseAdapter {

  private MandelbrotScreen display;

  private double newRealMin;
  private double newRealMax;
  private double newImaginaryMin;
  private double newImaginaryMax;

  public ZoomController(MandelbrotScreen display) {
    this.display = display;
  }

  @Override
  public void mousePressed(MouseEvent e) {

    int xOnScreen = e.getXOnScreen();
    int yOnScreen = e.getYOnScreen();

    System.out.println(String.format("clicked at x %d and y %d", xOnScreen, yOnScreen));

    newRealMin = display.convertXPositionToRealValue(xOnScreen);
    newImaginaryMax = display.convertYPositionToImaginaryValue(yOnScreen);
  }

  @Override
  public void mouseReleased(MouseEvent e) {

    int xOnScreen = e.getXOnScreen();
    int yOnScreen = e.getYOnScreen();

    System.out.println(String.format("released at x %d and y %d", xOnScreen, yOnScreen));

    newRealMax = display.convertXPositionToRealValue(xOnScreen);
    newImaginaryMin = display.convertYPositionToImaginaryValue(yOnScreen);

    display.updateRegion(newRealMin, newRealMax, newImaginaryMin, newImaginaryMax);
  }
}
