package ru.daniilazarnov.calc.dao;

import org.springframework.stereotype.Repository;
import ru.daniilazarnov.calc.converter.ActionConverter;
import ru.daniilazarnov.calc.domain.Action;
import ru.daniilazarnov.calc.domain.Actor;
import ru.daniilazarnov.calc.domain.Appraisal;
import ru.daniilazarnov.calc.domain.UnityLogRow;
import ru.daniilazarnov.calc.property.CalcProperties;

import java.util.*;

@Repository
public class MemoryDaoImpl implements MemoryDao {

    private final CalcProperties calcProperties;

    private final Map<String, Map<String, Appraisal>> memory = new HashMap<>();
    private final Map<String, Set<Actor>> memoryAboutActorInSession = new HashMap<>();
    private final Map<String, Action> actions = new HashMap<>();
    private final List<String> systemActions = new ArrayList<>();

    public MemoryDaoImpl(CalcProperties calcProperties) {
        this.calcProperties = calcProperties;

        calcProperties.getSystemActions().forEach(s -> systemActions.add(s.trim().toUpperCase()));
        calcProperties.getActions().forEach(action -> actions.put(action.getName().trim().toUpperCase(), ActionConverter.action_action.apply(action)));
    }

    @Override
    public void updateMemory(UnityLogRow unityLogRow) {

        String sessionId = unityLogRow.getSessionId();
        Actor actor = new Actor(unityLogRow.getActor());
        Actor target = unityLogRow.getActor().isEmpty() ? actor : new Actor(unityLogRow.getTarget());
        Action action = actions.get(unityLogRow.getAction().trim().toUpperCase());

        if (memory.containsKey(sessionId)) {
            if (!memoryAboutActorInSession.get(sessionId).contains(actor)) {
                initMemoryForActorInSession(sessionId, actor);
            }
            if (!memoryAboutActorInSession.get(sessionId).contains(target)) {
                initMemoryForActorInSession(sessionId, target);
            }
        } else {
            initNewSession(sessionId);
            initMemoryForActorInSession(sessionId, actor);
            initMemoryForActorInSession(sessionId, target);
        }

        if (systemActions.stream().anyMatch(action1 -> unityLogRow.getAction().trim().toUpperCase().startsWith(action1))) {
            return;
        }

        memory.get(sessionId).put(targetKey(actor, target), AppraisalFunctions.targetAppraisalFunc.apply(action.getAppraisal(), memory.get(sessionId).get(targetKey(actor, target))));
        memory.get(sessionId).put(actorKey(actor, target), AppraisalFunctions.actorAppraisalFunc.apply(action.getAppraisal(), memory.get(sessionId).get(actorKey(actor, target))));
    }

    @Override
    public String getAppraisalState(String sessionId) {
        return memory.get(sessionId).toString();
    }

    private void initNewSession(String sessionId) {
        memory.put(sessionId, new HashMap<>());
        memoryAboutActorInSession.put(sessionId, new HashSet<>());
    }

    private void initMemoryForActorInSession(String sessionId, Actor actor) {

        memoryAboutActorInSession.get(sessionId).add(actor);

        memoryAboutActorInSession.get(sessionId).forEach(actor1 -> {
            //FIXME определить нормальный формат для ключей, независмый от порядка передачи аргументов
            memory.get(sessionId).put(targetKey(actor, actor1), Appraisal.valueOf(calcProperties.getInitialValence(), calcProperties.getInitialDominance()));
            memory.get(sessionId).put(actorKey(actor, actor1), Appraisal.valueOf(calcProperties.getInitialValence(), calcProperties.getInitialDominance()));
        });
    }

    private static String actorKey(Actor actor, Actor target) {
        return formatKey(actor, target);
    }

    private static String targetKey(Actor actor, Actor target) {
        return formatKey(target, actor);
    }

    private static String formatKey(Actor from, Actor to) {
        return from.getName() + "To" + to.getName();
    }

}
