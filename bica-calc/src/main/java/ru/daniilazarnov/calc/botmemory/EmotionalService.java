package ru.daniilazarnov.calc.botmemory;

import org.springframework.stereotype.Service;
import ru.daniilazarnov.calc.property.CalcProperties;
import ru.daniilazarnov.common.model.*;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.daniilazarnov.common.functions.AppraisalFunctions.actorAppraisalFunc;
import static ru.daniilazarnov.common.functions.AppraisalFunctions.targetAppraisalFunc;

@Service
public class EmotionalService implements BotService {

    private final Map<String, Set<State>> memoryState = new HashMap<>();
    private final Appraisal initAppraisal;

    public EmotionalService(CalcProperties calcProperties) {
        initAppraisal = Appraisal.valueOf(calcProperties.getInitialValence(), calcProperties.getInitialDominance());
    }

    @Override
    public Event updateMemory(Event event) {

        String sessionId = event.getSessionId();
        Actor actor = event.getActor();
        Actor target = event.getTarget();
        Action action = event.getAction();

        if (!memoryState.containsKey(sessionId)) {
            initNewSession(sessionId);
            if (!actor.getName().isEmpty()) {
                initMemoryForActorInSession(sessionId, actor);
            }
            if (!target.getName().isEmpty()) {
                initMemoryForActorInSession(sessionId, target);
            }
        }

        if (!actor.getName().isEmpty() && isNotDetermined(sessionId, actor)) {
            initMemoryForActorInSession(sessionId, actor);
        }
        if (!target.getName().isEmpty() && isNotDetermined(sessionId, target)) {
            initMemoryForActorInSession(sessionId, target);
        }

        switch (event.getAction().getActionType()) {
            case NORMAL:
                updateAppraisal(sessionId, actor, target, action);
                break;
            case ALL:
                updateAllAppraisal(sessionId, actor, action);
                break;
            case SELF:
                updateSelfAppraisal(sessionId, actor, action);
                break;
            case SYSTEM:
            default:
                break;
        }

        event.getAppraisalStateSet().addAll(getAppraisalState(sessionId));

        return event;
    }

    private boolean isNotDetermined(String sessionId, Actor actor) {
        for (State state : memoryState.get(sessionId)) {
            if (state.getActor().equals(actor)) {
                return false;
            }
        }
        return true;
    }

    private void updateAppraisal(String sessionId, Actor actor, Actor target, Action action) {
        for (State state : memoryState.get(sessionId)) {
            if (state.getActor().equals(actor) && state.getTarget().equals(target)) {
                state.setAppraisal(actorAppraisalFunc.apply(action.getAppraisal(), state.getAppraisal()));
            }
            if (state.getActor().equals(target) && state.getTarget().equals(actor)) {
                state.setAppraisal(targetAppraisalFunc.apply(action.getAppraisal(), state.getAppraisal()));
            }
        }
    }

    private void updateAllAppraisal(String sessionId, Actor actor, Action action) {
        for (State state : memoryState.get(sessionId)) {
            if (state.getActor().equals(actor)) {
                state.setAppraisal(actorAppraisalFunc.apply(action.getAppraisal(), state.getAppraisal()));
            }
            if (state.getTarget().equals(actor)) {
                state.setAppraisal(targetAppraisalFunc.apply(action.getAppraisal(), state.getAppraisal()));
            }
        }
    }

    private void updateSelfAppraisal(String sessionId, Actor actor, Action action) {
        updateAppraisal(sessionId, actor, actor, action);
    }

    private Set<State> getAppraisalState(String sessionId) {
        return memoryState.get(sessionId);
    }

    private void initNewSession(String sessionId) {
        memoryState.put(sessionId, new LinkedHashSet<>());
    }

    private void initMemoryForActorInSession(String sessionId, Actor actor) {

        memoryState.get(sessionId).add(State.valueOf(actor, actor, initAppraisal));
        memoryState.get(sessionId).stream()
                .map(State::getActor)
                .collect(Collectors.toSet())
                .forEach(actor1 -> {
                    memoryState.get(sessionId).add(State.valueOf(actor, actor1, initAppraisal));
                    memoryState.get(sessionId).add(State.valueOf(actor1, actor, initAppraisal));
                });
    }

}
