package ru.daniilazarnov.bot.core.emotional;

import ru.daniilazarnov.common.model.Actor;
import ru.daniilazarnov.common.model.Operation;

public interface OperationService {

    void addScheduler(String sessionId, Actor actor);

    Operation getOperation();

}
