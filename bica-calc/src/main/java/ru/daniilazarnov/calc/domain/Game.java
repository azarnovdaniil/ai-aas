package ru.daniilazarnov.calc.domain;

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
