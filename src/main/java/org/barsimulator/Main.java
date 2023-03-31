package org.barsimulator;

import static org.barsimulator.Constants.AREA_BAR_POPULARITY;
import static org.barsimulator.Constants.AREA_BAR_SIZE;
import static org.barsimulator.Constants.AREA_DOOR_POPULARITY;
import static org.barsimulator.Constants.AREA_DOOR_SIZE;
import static org.barsimulator.Constants.AREA_SCENE_POPULARITY;
import static org.barsimulator.Constants.AREA_SCENE_SIZE;

import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        Area scene = new Area("Scene", AREA_SCENE_POPULARITY, AREA_SCENE_SIZE);
        Area barArea = new Area("Bar", AREA_BAR_POPULARITY, AREA_BAR_SIZE);
        Area door = new Area("Door", AREA_DOOR_POPULARITY, AREA_DOOR_SIZE);

        new BarSimulator().run(new Bar(scene, barArea, door), Collections.emptyList());
    }
}
