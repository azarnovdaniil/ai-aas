package ru.daniilazarnov.bot.core;

import ru.daniilazarnov.common.model.Actor;
import ru.daniilazarnov.common.model.Event;
import ru.daniilazarnov.common.model.Operation;

import java.util.Set;

public interface BotCore {

    Event actionHandle(Event event);

    void addOperations(String sessionId, Set<Operation> operations);

    void addScheduler(String sessionId, Actor actor);

    Operation executeOperation();

}
