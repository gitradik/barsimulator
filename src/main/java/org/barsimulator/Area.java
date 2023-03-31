package org.barsimulator;

import static org.barsimulator.Constants.AREA_MIN_SIZE;

import java.util.ArrayList;
import java.util.List;

import org.barsimulator.person.Guest;

public class Area {

    private final String name;
    private final int popularity;
    private final int areaSize;

    private final List<Guest> guestList;

    public boolean addGuest(Guest p) {
        if (hasFreeSeat()) {
            return guestList.add(p);
        }
        return false;
    }

    public boolean removeGuest(Guest p) {
        return guestList.remove(p);
    }

    public int getPopularity() {
        return popularity;
    }

    public int getAreaSize() {
        return areaSize;
    }

    public Area(String name, int popularity, int size) {
        this.name = name;
        this.popularity = popularity;
        this.areaSize = Math.max(size, AREA_MIN_SIZE);
        guestList = new ArrayList<>(size);
    }

    public boolean hasFreeSeat() {
        return guestList.size() < areaSize;
    }
}
