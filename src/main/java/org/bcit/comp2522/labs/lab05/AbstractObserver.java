package org.bcit.comp2522.labs.lab05;

/**
 * Observers are doing the looking, i.e., they are the subscribers.
 *
 * @author Gareth Ng
 * @version 1.0
 */
public abstract class AbstractObserver {
  public abstract void update(Object msg);
}
