package org.barsimulator.person;

import java.util.Objects;

import org.barsimulator.Bar;
import org.barsimulator.Constants;

abstract class AbstractPerson implements Person {

    private final String name;

    private Bar bar;

    protected AbstractPerson(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public Bar getBar() {
        return bar;
    }

    @Override
    public boolean enter(Bar bar) {
        if (Objects.isNull(this.bar) && bar.addPerson(this)) {
            this.bar = bar;
            System.out.println(getClass().getSimpleName() + " - \"" + name + "\" entered to Bar");
            return true;
        }
        return false;
    }

    @Override
    public void doSomething() {
        if(Objects.nonNull(bar)){
            doAfterCheck();
        }
    }

    protected abstract void doAfterCheck();

    @Override
    public void leave() {
        if (Objects.nonNull(bar)) {
            bar.leave(this);
            bar = null;

            System.out.println(Constants.Color.ANSI_RED +
                    getClass().getSimpleName()
                    + " - \"" + getName() + "\" leaved from Bar" + Constants.Color.ANSI_RESET);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AbstractPerson that = (AbstractPerson) o;
        return Objects.equals(name, that.name) && Objects.equals(bar, that.bar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, bar);
    }
}
