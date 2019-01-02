package ru.daniilazarnov.bot.paradigm;

import ru.daniilazarnov.common.model.data.Actor;
import ru.daniilazarnov.common.model.data.Event;
import ru.daniilazarnov.common.model.data.Operation;

public interface ParadigmService {

    Event eventHandle(Event eventTO);

    Operation executeOperation();

    String getUrl();

    void initSession(String sessionId);

    boolean isInitSession(String sessionId);

    void addBot(String sessionId, Actor actor);

}
