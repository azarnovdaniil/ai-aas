package ru.daniilazarnov.common.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.daniilazarnov.common.serialization.json.deserializers.ActorDeserializer;
import ru.daniilazarnov.common.serialization.json.serializers.ActorSerializer;

import java.util.Objects;

@JsonSerialize(using = ActorSerializer.class)
@JsonDeserialize(using = ActorDeserializer.class)
public class Actor {

    private static final Actor NONE = new Actor("none");

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

    public static Actor none() {
        return NONE;
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

    @Override
    public String toString() {
        return "Actor{" +
                "name='" + name + '\'' +
                '}';
    }
}
