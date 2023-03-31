package org.barsimulator.person;

import static org.barsimulator.Constants.*;
import org.barsimulator.menu.Drink;

public class Waiter extends AbstractPerson {

    public Waiter(String name) {
        super(name);
    }

    @Override
    protected void doAfterCheck() {

    }

    public float serve(Drink drink, Guest guest) {
        System.out.println(Color.ANSI_GREEN
                + getClass().getSimpleName()
                + " - \""
                + getName()
                + "\" accepts an order"
                + Color.ANSI_RESET);
        return guest.give(drink);
    }
}
