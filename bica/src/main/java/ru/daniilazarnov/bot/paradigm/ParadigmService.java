package ru.daniilazarnov.bot.paradigm;

import ru.daniilazarnov.common.model.Actor;
import ru.daniilazarnov.common.model.Event;
import ru.daniilazarnov.common.model.Operation;

public interface ParadigmService {

    void eventHandle(Event eventTO);

    Operation executeOperation();

    String getUrl();

    void addSession(String sessionId);

    boolean isInitSession(String sessionId);

    void addBot(String sessionId, Actor actor);

}
