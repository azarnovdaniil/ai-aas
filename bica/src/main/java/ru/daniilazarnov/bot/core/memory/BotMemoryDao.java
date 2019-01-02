package ru.daniilazarnov.bot.core.memory;

import org.springframework.stereotype.Repository;
import ru.daniilazarnov.common.config.GameConfig;
import ru.daniilazarnov.common.model.Action;
import ru.daniilazarnov.common.model.Actor;
import ru.daniilazarnov.common.model.Appraisal;
import ru.daniilazarnov.common.model.State;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static ru.daniilazarnov.common.functions.AppraisalFunctions.actorAppraisalFunc;
import static ru.daniilazarnov.common.functions.AppraisalFunctions.targetAppraisalFunc;

@Repository
public class BotMemoryDao implements MemoryDao {

    private final Map<String, Set<State>> memoryState = new ConcurrentHashMap<>();
    private final Appraisal initAppraisal;

    public BotMemoryDao(GameConfig gameConfig) {
        initAppraisal = gameConfig.getInitialAppraisal();
    }

    @Override
    public boolean isInitSession(String sessionId) {
        return memoryState.containsKey(sessionId);
    }

    @Override
    public void updateAppraisal(String sessionId, Actor actor, Actor target, Action action) {
        for (State state : memoryState.get(sessionId)) {
            if (state.getActor().equals(actor) && state.getTarget().equals(target)) {
                state.setAppraisal(actorAppraisalFunc.apply(action.getAppraisal(), state.getAppraisal()));
            }
            if (state.getActor().equals(target) && state.getTarget().equals(actor)) {
                state.setAppraisal(targetAppraisalFunc.apply(action.getAppraisal(), state.getAppraisal()));
            }
        }
    }

    @Override
    public void updateAllAppraisal(String sessionId, Actor actor, Action action) {
        for (State state : memoryState.get(sessionId)) {
            if (state.getActor().equals(actor)) {
                state.setAppraisal(actorAppraisalFunc.apply(action.getAppraisal(), state.getAppraisal()));
            }
            if (state.getTarget().equals(actor)) {
                state.setAppraisal(targetAppraisalFunc.apply(action.getAppraisal(), state.getAppraisal()));
            }
        }
    }

    @Override
    public void updateSelfAppraisal(String sessionId, Actor actor, Action action) {
        updateAppraisal(sessionId, actor, actor, action);
    }

    @Override
    public Set<State> getAppraisalState(String sessionId) {
        return memoryState.get(sessionId);
    }

    @Override
    public void initNewSession(String sessionId) {
        memoryState.put(sessionId, new LinkedHashSet<>());
    }

    @Override
    public void initMemoryForActorInSession(String sessionId, Actor actor) {

        memoryState.get(sessionId).add(State.valueOf(actor, actor, initAppraisal));
        memoryState.get(sessionId).stream()
                .map(State::getActor)
                .collect(Collectors.toSet())
                .forEach(actor1 -> {
                    memoryState.get(sessionId).add(State.valueOf(actor, actor1, initAppraisal));
                    memoryState.get(sessionId).add(State.valueOf(actor1, actor, initAppraisal));
                });
    }

    @Override
    public boolean isNotDetermined(String sessionId, Actor actor) {
        for (State state : memoryState.get(sessionId)) {
            if (state.getActor().equals(actor)) {
                return false;
            }
        }
        return true;
    }

}
