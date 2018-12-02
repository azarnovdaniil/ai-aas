package ru.daniilazarnov.calc.converter;

import org.springframework.stereotype.Component;
import ru.daniilazarnov.calc.model.Actor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Component
public class ActorFactory {

    private final Map<String, HashSet<Actor>> sessionsWithActors = new HashMap<>();

    Actor getActor(String sessionId, String actorName) {
        if (sessionsWithActors.containsKey(sessionId)) {
            for (Actor actor : sessionsWithActors.get(sessionId)) {
                if (actor.getName().equals(actorName)) {
                    return actor;
                }
            }
            Actor actor = Actor.valueOf(actorName);
            sessionsWithActors.get(sessionId).add(actor);

            return actor;
        } else {
            Actor actor = Actor.valueOf(actorName);
            sessionsWithActors.put(sessionId, new HashSet<>(List.of(actor)));
            return actor;
        }
    }

}
