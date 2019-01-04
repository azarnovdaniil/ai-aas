package ru.daniilazarnov.bot.core.rational;

import ru.daniilazarnov.common.model.entity.Actor;
import ru.daniilazarnov.common.model.entity.Operation;

import java.util.Set;

public interface RationalService {

    Set<Operation> getOperations(String sessionId, Actor actor);

    void addOperations(String sessionId, Set<Operation> operations);

}
