package ru.daniilazarnov.bot.core.emotional;

import ru.daniilazarnov.common.model.entity.Actor;
import ru.daniilazarnov.common.model.entity.Operation;

public interface OperationService {

    void addScheduler(String sessionId, Actor actor);

    Operation getOperation();

}
