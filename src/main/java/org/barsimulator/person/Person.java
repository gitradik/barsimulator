package org.barsimulator.person;

import org.barsimulator.Bar;

public interface Person {

    String getName();

    boolean enter(Bar bar);

    void doSomething();

    void leave();

}
