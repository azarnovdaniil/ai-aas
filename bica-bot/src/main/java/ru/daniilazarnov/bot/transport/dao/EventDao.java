package ru.daniilazarnov.bot.transport.dao;

import ru.daniilazarnov.bot.transport.domain.Event;

import java.util.List;

public interface EventDao {

    List<Event> getListEvent();

}
