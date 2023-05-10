package org.bcit.comp2522.labs.lab05;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import processing.core.PVector;


/**
 * The Flock manages a list of Boid objects.
 *
 * @author Gareth Ng (based on Daniel Shiffman's Boids)
 * @version 1.0
 */
public class Flock extends AbstractObservable {
  ArrayList<IMoveable> boids; // An ArrayList for all the boids
  Color color;
  PVector position;
  Home home;
  float homeRadius;
  BoidRenderer boidRenderer;
  HomeRenderer homeRenderer;
  private boolean isFlocking = true;

  /**
   * Constructor for a Flock.
   *
   * @param window the processing window
   * @param generator a random number generator
   */
  public Flock(Window window, Random generator) {
    this.boids = new ArrayList<>(); // Initialize the ArrayList
    this.position = new PVector(
      generator.nextFloat() * window.width,
      generator.nextFloat() * window.height
    );
    this.color = new Color(
      64 + ((int) (generator.nextFloat() * 255 / 2f)),
      64 + ((int) (generator.nextFloat() * 255 / 2f)),
      64 + ((int) (generator.nextFloat() * 255 / 2f))
    );
    PVector velocity = new PVector(
        generator.nextFloat(),
        generator.nextFloat()
    );
    homeRadius = 10f;
    home = new Home(this.position, velocity, homeRadius, color);
    boidRenderer = new BoidRenderer();
    homeRenderer = new HomeRenderer();
  }

  /**
   * Check whether the Home is under the mouse when clicked.
   *
   * @param mouseX from window
   * @param mouseY from window
   */
  public void checkClick(float mouseX, float mouseY) {
    PVector mousePos = new PVector(mouseX, mouseY);
    PVector homePos = home.getPosition();
    if (mousePos.dist(homePos) <= home.getRadius()) {
      this.isFlocking = !isFlocking;
      notifyObservers();
    }
  }

  /**
   * Render all IMoveables.
   *
   * @param window is processing window
   */
  public void run(Window window) {
    for (IMoveable b : boids) {
      b.run(window, boids);  // Passing the entire list of boids to each boid individually
      boidRenderer.render(window, b);
    }
    homeRenderer.render(window, home);
  }

  public ArrayList<IMoveable> getBoids() {
    return boids;
  }

  public void addBoid() {
    Boid b = new Boid(position.x, position.y, color);
    boids.add(b);
  }
  // Method overloading
  public void addBoid(Boid b) {
    boids.add(b);
  }

  public void removeBoid(Boid b) {
    boids.remove(b);
  }

  /**
   * Check if one of the boid from another flock
   * touched the home of this flock.
   *
   * @param flock as other flock
   * @return Boid that touched this flock
   */
  public Boid isTouched(Flock flock) {
    if (flock == this) {
      return null;
    }

    float trueRadius = home.getRadius() / 2f;
    for (IMoveable m : flock.getBoids()) {
      PVector boidPoint = new PVector(m.getPosition().x, m.getPosition().y);
      if (home.getPosition().dist(boidPoint) <= trueRadius) {
        return (Boid) m;
      }
    }
    return null;
  }

  public void touchBehaviour(Flock flock, Boid b) {
    flock.unregisterObserver(b);
    registerObserver(b);
  }

  @Override
  public void registerObserver(AbstractObserver observer) {
    Boid newBoid = (Boid) observer;
    newBoid.setColor(this.color);
    addBoid(newBoid);
    notifyObservers();
  }

  @Override
  public void unregisterObserver(AbstractObserver observer) {
    Boid oldBoid = (Boid) observer;
    removeBoid(oldBoid);
  }

  @Override
  public void notifyObservers() {
    for (IMoveable m : boids) {
      if (m instanceof Boid b) {
        b.update(isFlocking ? new FlockingBehaviour(b) : new HomeBehaviour(home, b));
      }
    }
  }
}