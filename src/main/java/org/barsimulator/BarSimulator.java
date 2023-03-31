package org.barsimulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import org.barsimulator.person.Guest;
import org.barsimulator.person.Musician;
import org.barsimulator.person.Person;
import org.barsimulator.person.Waiter;

public class BarSimulator {

    private static final Random random = new Random();

    private static final MusicalInstrument[] instruments = new MusicalInstrument[] {
            MusicalInstrument.GUITAR,
            MusicalInstrument.SAXOPHONE,
            MusicalInstrument.VIOLIN
    };

    public void run(Bar bar, List<Person> persons) {
        int randomPersonCount = random.nextInt(15);

        List<Person> newPersonList = new ArrayList<>(persons);
        for (int i = 0; i < randomPersonCount; ++i) {
            int randomPerson = random.nextInt(5);
            Person p = createPerson(randomPerson, newPersonList.size() + 1);
            newPersonList.add(p);
        }

        newPersonList.forEach(p -> {
            p.enter(bar);
            if (Objects.nonNull(bar)) {
                p.doSomething();
            }
            waitFor(200);
        });

        run(bar, newPersonList);
    }

    private Person createPerson(int randomPerson, int pNum) {
        if (randomPerson == 1) {
            return new Waiter("Waiter #" + pNum);
        } else if (randomPerson == 2) {
            return new Musician("Musician #" + pNum, instruments[random.nextInt(3)]);
        } else {
            return new Guest("Guest #" + pNum);
        }
    }

    public static void waitFor(int millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
