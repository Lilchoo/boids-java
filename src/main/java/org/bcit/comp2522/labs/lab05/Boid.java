package org.bcit.comp2522.labs.lab05;

import java.awt.Color;
import java.util.ArrayList;
import processing.core.PVector;


/**
 * The Boid class manages the properties of the Boids.
 *
 * @author Gareth Ng (based on Daniel Shiffman's code)
 * @version 1.0

 */
public class Boid extends AbstractObserver implements IMoveable {
  private PVector position;
  private PVector velocity;
  private PVector acceleration;
  private Color color;
  private float radius;
  private float maxforce;    // Maximum steering force
  private float maxspeed;    // Maximum speed
  private AbstractBehaviour behaviour;

  /**
   * Constructor for a Boid.
   *
   * @param x should be within window size limits
   * @param y should be within window size limits
   * @param color RGB colour
   */
  public Boid(float x, float y, Color color) {
    this.acceleration = new PVector(0, 0);
    float angle = (float) (Math.random() * Math.PI * 2);
    this.velocity = new PVector((float) Math.cos(angle), (float) Math.sin(angle));
    this.position = new PVector(x, y);
    this.color = color;
    this.radius = 5.0f;
    this.maxspeed = 2;
    this.maxforce = 0.03f;
    this.behaviour = new FlockingBehaviour(this);
  }

  @Override
  public void update(Object msg) {
    if (msg instanceof AbstractBehaviour newBehaviour) {
      this.behaviour = newBehaviour;
    }
  }

  public void run(Window window, ArrayList<IMoveable> moveables) {
    behaviour.recalculate(moveables);
    behaviour.move();
  }

  // Getters and Setters
  public AbstractBehaviour getBehaviour() {
    return behaviour;
  }

  public float getRadius() {
    return radius;
  }

  public float getMaxforce() {
    return maxforce;
  }

  public float getMaxspeed() {
    return maxspeed;
  }

  public PVector getPosition() {
    return position;
  }

  public PVector getVelocity() {
    return velocity;
  }

  public PVector getAcceleration() {
    return acceleration;
  }

  @Override
  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public void setBehaviour(AbstractBehaviour behaviour) {
    this.behaviour = behaviour;
  }

  public void setRadius(float radius) {
    this.radius = radius;
  }

  public void setMaxspeed(float maxspeed) {
    this.maxspeed = maxspeed;
  }

  public void setMaxforce(float maxforce) {
    this.maxforce = maxforce;
  }

  public void setPosition(PVector position) {
    this.position = position;
  }

  public void setVelocity(PVector velocity) {
    this.velocity = velocity;
  }

  public void setAcceleration(PVector acceleration) {
    this.acceleration = acceleration;
  }

}
