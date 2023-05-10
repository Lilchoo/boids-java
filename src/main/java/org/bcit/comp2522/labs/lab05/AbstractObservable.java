package org.bcit.comp2522.labs.lab05;

/**
 * Observables are things that can be observed. In publisher/subscriber
 * language, they are the publishers.
 *
 * @author Gareth Ng
 * @version 1.0
 */
public abstract class AbstractObservable {

  public abstract void registerObserver(AbstractObserver observer);

  public abstract void unregisterObserver(AbstractObserver observer);

  public abstract void notifyObservers();
}
