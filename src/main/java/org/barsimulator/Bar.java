package org.barsimulator;

import static org.barsimulator.Constants.BAR_ENTER_GUEST_MAX_ALCLEVEL;
import static org.barsimulator.Constants.Color;
import static org.barsimulator.Constants.MUSICIAN_MAX_SIZE;
import static org.barsimulator.Constants.WAITER_SIZE_DIVISOR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.barsimulator.menu.Drink;
import org.barsimulator.person.Guest;
import org.barsimulator.person.Musician;
import org.barsimulator.person.Person;
import org.barsimulator.person.Waiter;

public class Bar {

    private final List<Area> areaList;
    private final List<Waiter> waiterList;
    private final List<Musician> musicianList;

    private float cashBox;
    private final int maxWaiters;

    public Bar(Area... areas) {
        areaList = Arrays.stream(areas).sorted(Comparator.comparing(Area::getPopularity).reversed()).toList();
        int barSize = areaList.stream().map(Area::getAreaSize).reduce(0, Integer::sum);

        waiterList = new ArrayList<>();
        musicianList = new ArrayList<>();

        maxWaiters = barSize / WAITER_SIZE_DIVISOR;
        cashBox = 0f;
    }

    public boolean addPerson(Person p) {
        if (p instanceof Guest guest) {
            Optional<Area> freeArea = getFreeArea();
            return freeArea.isPresent() && guest.getAlcoholLevel() < BAR_ENTER_GUEST_MAX_ALCLEVEL && freeArea.get().addGuest(guest);
        } else if (waiterList.size() < maxWaiters && p instanceof Waiter waiter) {
            System.out.println(getClass().getSimpleName() + " - \"" + (waiterList.size() + 1) + "\" waiters in Bar");
            return waiterList.add(waiter);
        } else if (musicianList.size() < MUSICIAN_MAX_SIZE && p instanceof Musician musician) {
            System.out.println(getClass().getSimpleName() + " - \"" + (musicianList.size() + 1) + "\" musicians in Bar");
            return musicianList.add(musician);
        }
        return false;
    }

    public void order(Drink d, Guest g) {
        if (waiterList.isEmpty()) {
            System.out.print(Color.ANSI_RED + getClass().getSimpleName()
                    + " - order not completed for guest \"" + g.getName() + "\"");
        } else {
            Waiter w = waiterList.get(new Random().nextInt(waiterList.size()));
            cashBox += w.serve(d, g);
            System.out.print(Color.ANSI_GREEN + getClass().getSimpleName() + " - \"" + w.getName() + "\" completed order");
        }
        System.out.print(" [cashBox=\"");
        System.out.printf("%.2f", cashBox);
        System.out.print("\"]");
        System.out.println(Color.ANSI_RESET);
    }

    private Optional<Area> getFreeArea() {
        for (Area a : areaList) {
            if (a.hasFreeSeat()) {
                return Optional.of(a);
            }
        }
        return Optional.empty();
    }

    public void leave(Person p) {
        if (p instanceof Waiter waiter) {
            waiterList.remove(waiter);
        }
        if (p instanceof Musician musician) {
            musicianList.remove(musician);
        }
        if (p instanceof Guest guest) {
            areaList.forEach(a -> a.removeGuest(guest));
        }
    }
}
