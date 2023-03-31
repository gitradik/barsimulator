package org.barsimulator.menu;

import java.util.Random;

public class Menu {
    public static Drink whiskeyWithCola = new Drink("Whiskey with Cola", 3.2f, 10);
    public static Drink molotowcocktail = new Drink("Molotowcocktail", 4.6f, 22);
    public static Drink beer = new Drink("Beer", 2.0f, 5);

    public static Drink getRandom() {
        Drink[] drinks = new Drink[]{whiskeyWithCola, molotowcocktail, beer};
        return drinks[new Random().nextInt(drinks.length)];
    }
}
