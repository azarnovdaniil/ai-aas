package ru.daniilazarnov.bot.core.rational;

import ru.daniilazarnov.common.model.Actor;
import ru.daniilazarnov.common.model.Operation;

import java.util.List;

public interface RationalService {

    List<Operation> getOperations(String sessionId, Actor actor);

    void addOperation(String sessionId, Actor actor, Operation operation);

}
