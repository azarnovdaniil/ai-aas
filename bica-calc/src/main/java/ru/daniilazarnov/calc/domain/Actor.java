package ru.daniilazarnov.calc.domain;

import java.util.Objects;

public class Actor {

    private final String name;

    private Actor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Actor valueOf(String name) {
        return new Actor(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actor actor = (Actor) o;

        return Objects.equals(name, actor.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
