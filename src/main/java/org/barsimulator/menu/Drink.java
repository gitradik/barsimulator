package org.barsimulator.menu;

import static org.barsimulator.Constants.*;

public record Drink(String name, float price, int alcoholLevel) {

    public Drink(String name, float price, int alcoholLevel) {
        this.name = name;
        this.price = price;
        this.alcoholLevel = Math.max(alcoholLevel, MENU_MIN_ALCOHOL_LEVEL);
    }
}
