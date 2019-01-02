package ru.daniilazarnov.bot.core;

import ru.daniilazarnov.common.model.Event;
import ru.daniilazarnov.common.model.Operation;

import java.util.Set;

public interface BotCore {

    void actionHandle(Event event);

    void addOperations(String sessionId, Set<Operation> operations);

    Operation executeOperation();

}
