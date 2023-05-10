package org.bcit.comp2522.labs.lab05;

import java.util.ArrayList;

/**
 * AbstractBehaviour class. Concrete instantiations will provide a
 * behaviour for IMoveables relative to other IMoveables.
 *
 * @author Gareth Ng
 * @version 1.0
 */
public abstract class AbstractBehaviour {
  public abstract void recalculate(ArrayList<IMoveable> moveables);

  public abstract void move();
}
