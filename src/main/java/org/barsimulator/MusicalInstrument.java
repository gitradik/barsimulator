package org.barsimulator;

public enum MusicalInstrument {
    GUITAR("Guitar"),
    VIOLIN("Violin"),
    SAXOPHONE("Saxophone");

    private final String name;

    MusicalInstrument(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
