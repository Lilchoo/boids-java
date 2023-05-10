package org.bcit.comp2522.labs.lab05;

import java.awt.Color;
import java.util.ArrayList;
import processing.core.PVector;


/**
 * IMoveable is anything that is movable (and drawable) onscreen.
 *
 * @author Gareth Ng
 * @version 1.0
 */
public interface IMoveable {
  PVector getPosition();

  PVector getVelocity();

  float getRadius();

  Color getColor();

  void run(Window window, ArrayList<IMoveable> moveables);

}
