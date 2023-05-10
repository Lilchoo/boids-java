package org.bcit.comp2522.labs.lab05;

import java.util.ArrayList;
import processing.core.PVector;

/**
 * Flocking will make Boids roughly follow each other.
 *
 * @author Gareth Ng
 * @version 1.0
 */
public class HomeBehaviour extends AbstractBehaviour {
  Home home;
  Boid boid;

  public HomeBehaviour(Home home, Boid boid) {
    this.home = home;
    this.boid = boid;
  }

  /**
   * Update Boid's position.
   */
  public void move() {
    // Update velocity
    boid.getVelocity().add(boid.getAcceleration());
    // Limit speed
    boid.getVelocity().limit(boid.getMaxspeed());
    boid.getPosition().add(boid.getVelocity());
    // Reset acceleration to 0 each cycle
    boid.getAcceleration().mult(0);
  }

  /**
   * Find location of homes and recalculate acceleration of boids.
   *
   * @param moveables Boids
   */
  public void recalculate(ArrayList<IMoveable> moveables) {
    PVector seekAcceleration = seek(home.getPosition());
    // Arbitrarily weight these forces
    seekAcceleration.mult(20f);
    // Add the force vectors to acceleration
    applyForce(seekAcceleration);
  }

  /**
   * Seek: A method that calculates and applies a
   * steering force towards a target.
   * STEER = DESIRED MINUS VELOCITY
   *
   * @param target the direction to steer towards
   * @return PVector updated steering vector
   */
  public PVector seek(PVector target) {
    // A vector pointing from the position to the target
    PVector desired = PVector.sub(target, boid.getPosition());
    // Scale to maximum speed
    desired.normalize();
    desired.mult(boid.getMaxspeed());
    // Steering = Desired minus Velocity
    PVector steer = PVector.sub(desired, boid.getVelocity());
    steer.limit(boid.getMaxforce());  // Limit to maximum steering force
    return steer;
  }

  public void applyForce(PVector force) {
    // We could add mass here if we want A = F / M
    boid.setAcceleration(boid.getAcceleration().add(force));
  }
}
