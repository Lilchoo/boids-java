package org.bcit.comp2522.labs.lab05;

import java.awt.Color;
import processing.core.PVector;

/**
 * HomeRenderer draws the IMovable as a Home.
 *
 * @author Gareth Ng
 * @version 1.0
 */
public class HomeRenderer extends AbstractRenderer {
  @Override
  public void render(Window window, IMoveable moveable) {
    Color c = moveable.getColor();
    window.fill(c.getRed(), c.getGreen(), c.getGreen());
    PVector pos = moveable.getPosition();
    float rad = moveable.getRadius();
    window.ellipse(pos.x, pos.y, rad, rad);
  }
}
