package ru.daniilazarnov.common.model.data;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.daniilazarnov.common.model.serialization.json.serializers.GameSerializer;

import java.util.List;

@JsonSerialize(using = GameSerializer.class)
public class Game {

    private List<Event> events;

    public Game(List<Event> events) {
        this.events = events;
    }

    public List<Event> getEvents() {
        return events;
    }

    public static Game of(List<Event> events) {
        return new Game(events);
    }
}
