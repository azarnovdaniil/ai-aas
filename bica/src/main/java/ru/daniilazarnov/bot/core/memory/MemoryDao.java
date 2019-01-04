package ru.daniilazarnov.bot.core.memory;

import ru.daniilazarnov.common.model.entity.Action;
import ru.daniilazarnov.common.model.entity.Actor;
import ru.daniilazarnov.common.model.entity.State;

import java.util.Set;

public interface MemoryDao {

    boolean isInitSession(String sessionId);

    void updateAppraisal(String sessionId, Actor actor, Actor target, Action action);

    void updateAllAppraisal(String sessionId, Actor actor, Action action);

    void updateSelfAppraisal(String sessionId, Actor actor, Action action);

    Set<State> getAppraisalState(String sessionId);

    void initSession(String sessionId);

    void initMemoryForActorInSession(String sessionId, Actor actor);

    boolean isNotDetermined(String sessionId, Actor actor);

}
