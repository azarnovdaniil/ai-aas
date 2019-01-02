package ru.daniilazarnov.bot.core.emotional;

import ru.daniilazarnov.common.model.data.Actor;
import ru.daniilazarnov.common.model.data.Operation;

public interface OperationService {

    void addScheduler(String sessionId, Actor actor);

    Operation getOperation();

}
