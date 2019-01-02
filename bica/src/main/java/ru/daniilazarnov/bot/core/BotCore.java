package ru.daniilazarnov.bot.core;

import ru.daniilazarnov.common.model.data.Actor;
import ru.daniilazarnov.common.model.data.Event;
import ru.daniilazarnov.common.model.data.Operation;

import java.util.Set;

public interface BotCore {

    Event actionHandle(Event event);

    void addOperations(String sessionId, Set<Operation> operations);

    void addScheduler(String sessionId, Actor actor);

    Operation executeOperation();

}
