package ru.daniilazarnov.bot.paradigm;

import ru.daniilazarnov.common.model.Event;
import ru.daniilazarnov.common.model.Operation;

public interface ParadigmService {

    void eventHandle(Event eventTO);

    Operation executeOperation();

    String getUrl();

    boolean addSession(String sessionId);

}
