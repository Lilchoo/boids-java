package org.bcit.comp2522.labs.lab05;

import java.awt.Color;
import java.util.ArrayList;
import processing.core.PVector;

/**
 * Home is the Flock's home position. Also draws circle.
 *
 * @author Gareth Ng
 * @version 1.0
 */

public class Home implements IMoveable {
  private final PVector position;
  private final PVector velocity;
  private final float radius;
  private final Color color;

  /**
   * Constructor for Home.
   *
   * @param position on window screen
   * @param velocity of the home (if they should move)
   * @param radius size of the Home for drawing, colliding and clicking
   * @param color RGB color
   */
  public Home(PVector position, PVector velocity, float radius, Color color) {
    this.position = position;
    this.velocity = velocity;
    this.radius = radius;
    this.color = color;
  }

  @Override
  public void run(Window window, ArrayList<IMoveable> moveables) {
  }

  @Override
  public float getRadius() {
    return radius;
  }

  @Override
  public PVector getVelocity() {
    return velocity;
  }

  @Override
  public PVector getPosition() {
    return position;
  }

  @Override
  public Color getColor() {
    return color;
  }
}
