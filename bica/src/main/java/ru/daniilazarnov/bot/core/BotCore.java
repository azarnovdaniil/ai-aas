package ru.daniilazarnov.bot.core;

import ru.daniilazarnov.common.model.entity.Actor;
import ru.daniilazarnov.common.model.entity.Event;
import ru.daniilazarnov.common.model.entity.Operation;

import java.util.Set;

public interface BotCore {

    Event eventHandler(Event event);

    void addOperations(String sessionId, Set<Operation> operations);

    void addScheduler(String sessionId, Actor actor);

    Operation executeOperation();

}
