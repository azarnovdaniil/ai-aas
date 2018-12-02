package ru.daniilazarnov.calc.model;

import java.util.List;

public class Game {

    private List<Event> events;

    public Game(List<Event> events) {
        this.events = events;
    }

    public List<Event> getEvents() {
        return events;
    }
}
