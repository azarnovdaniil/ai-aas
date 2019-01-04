package ru.daniilazarnov.common.model.entity;

import java.util.Objects;

public abstract class GameObject {

    private final String name;
    private final String type;

    public GameObject(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameObject actor = (GameObject) o;

        return Objects.equals(name, actor.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
