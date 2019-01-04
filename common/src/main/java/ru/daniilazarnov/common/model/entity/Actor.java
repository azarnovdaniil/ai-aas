package ru.daniilazarnov.common.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.daniilazarnov.common.model.serialization.json.deserializers.ActorDeserializer;
import ru.daniilazarnov.common.model.serialization.json.serializers.ActorSerializer;

@JsonSerialize(using = ActorSerializer.class)
@JsonDeserialize(using = ActorDeserializer.class)
public class Actor extends GameObject {

    private static final Actor NONE = new Actor("none");

    private Actor(String name) {
        super(name, Actor.class.getSimpleName());
    }

    public static Actor valueOf(String name) {
        if (name == null) {
            return NONE;
        }
        return new Actor(name);
    }

    public static Actor none() {
        return NONE;
    }

    public boolean isNotNone() {
        return !this.equals(NONE);
    }

}
