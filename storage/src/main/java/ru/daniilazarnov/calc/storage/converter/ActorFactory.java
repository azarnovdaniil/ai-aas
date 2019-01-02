package ru.daniilazarnov.calc.storage.converter;

import org.springframework.stereotype.Component;
import ru.daniilazarnov.common.model.Actor;

import java.util.HashMap;
import java.util.Map;

@Component
public class ActorFactory {

    private final Map<String, Map<String, Actor>> sessionsWithActors = new HashMap<>();

    Actor getActor(String sessionId, String actorName) {
        if (sessionsWithActors.containsKey(sessionId)) {
            Map<String, Actor> actorMap = sessionsWithActors.get(sessionId);

            if (actorMap.containsKey(actorName)) {
                return actorMap.get(actorName);
            } else {
                Actor actor = Actor.valueOf(actorName);
                actorMap.put(actorName, actor);
                return actor;
            }
        } else {
            Actor actor = Actor.valueOf(actorName);
            sessionsWithActors.put(sessionId, new HashMap<>(Map.of(actorName, actor)));
            return actor;
        }
    }

}
