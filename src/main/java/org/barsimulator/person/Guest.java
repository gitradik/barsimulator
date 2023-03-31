package org.barsimulator.person;

import static org.barsimulator.Constants.Color;
import static org.barsimulator.Constants.GUEST_DOSOMETHING_ORDER_RULE;

import java.util.Objects;
import java.util.Random;

import org.barsimulator.menu.Drink;
import org.barsimulator.menu.Menu;

public class Guest extends AbstractPerson {

    private int alcoholLevel = 0;

    public Guest(String name) {
        super(name);
    }

    public int getAlcoholLevel() {
        return alcoholLevel;
    }

    @Override
    public String toString() {
        return "Guest{" +
                getName() +
                ", alcoholLevel=" + alcoholLevel +
                '}';
    }

    @Override
    protected void doAfterCheck() {
        int rnd = new Random().nextInt(5);

        if (rnd <= GUEST_DOSOMETHING_ORDER_RULE) {
            System.out.println(Color.ANSI_CYAN
                    + getClass().getSimpleName()
                    + " - \""
                    + getName()
                    + "\" makes an order"
                    + Color.ANSI_RESET);
            getBar().order(Menu.getRandom(), this);
        } else {
            leave();
        }
    }

    public float give(Drink drink) {
        alcoholLevel += drink.alcoholLevel();
        System.out.println(getClass().getSimpleName()
                + " - \""
                + getName()
                + "\" Drinking a drink ["
                + "alcoholLevel="
                + alcoholLevel
                + "]"
        );
        return drink.price();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Guest guest = (Guest) o;
        return alcoholLevel == guest.alcoholLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), alcoholLevel);
    }
}
