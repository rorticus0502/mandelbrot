package com.projects.fun.screen.mandelbrot;

public class MandelbrotApplication {

  public static void main(String[] args) {

    MandelbrotScreen screen = new MandelbrotScreen();

    screen.addMouseListener(new ZoomController(screen));
    screen.setVisible(true);

  }

}
