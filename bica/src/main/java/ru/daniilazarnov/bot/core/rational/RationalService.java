package ru.daniilazarnov.bot.core.rational;

import ru.daniilazarnov.common.model.data.Actor;
import ru.daniilazarnov.common.model.data.Operation;

import java.util.Set;

public interface RationalService {

    Set<Operation> getOperations(String sessionId, Actor actor);

    void addOperations(String sessionId, Set<Operation> operations);

}
