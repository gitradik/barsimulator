package org.barsimulator.person;

import static org.barsimulator.Constants.Color;

import java.util.Objects;

import org.barsimulator.MusicalInstrument;

public class Musician extends AbstractPerson {

    private final MusicalInstrument musicalInstrument;

    public Musician(String name, MusicalInstrument musicalInstrument) {
        super(name);
        this.musicalInstrument = musicalInstrument;
    }

    @Override
    protected void doAfterCheck() {
        System.out.println(Color.ANSI_CYAN
                + getClass().getSimpleName()
                + " - \""
                + getName()
                + "\" play in musical instrument: \""
                + musicalInstrument.getName()
                + "\""
                + Color.ANSI_RESET);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Musician musician = (Musician) o;
        return Objects.equals(musicalInstrument, musician.musicalInstrument);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), musicalInstrument);
    }
}
