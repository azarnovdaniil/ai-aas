package ru.daniilazarnov.bot.paradigm;

import ru.daniilazarnov.common.model.entity.Actor;
import ru.daniilazarnov.common.model.entity.Event;
import ru.daniilazarnov.common.model.entity.Operation;

public interface ParadigmService {

    Event updateHandler(Event eventTO);

    Operation executeOperation();

    void initSession(String sessionId);

    boolean isInitSession(String sessionId);

    void addBot(String sessionId, Actor actor);

}
