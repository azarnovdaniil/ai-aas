package ru.daniilazarnov.bot.paradigm.teleport;

import org.springframework.stereotype.Service;
import ru.daniilazarnov.bot.core.BotCore;
import ru.daniilazarnov.bot.paradigm.ParadigmService;
import ru.daniilazarnov.common.config.GameConfig;
import ru.daniilazarnov.common.model.entity.Actor;
import ru.daniilazarnov.common.model.entity.Event;
import ru.daniilazarnov.common.model.entity.Operation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class TeleportService implements ParadigmService {

    private final BotCore botCore;
    private final GameConfig gameConfig;
    private final Map<String, Set<Actor>> sessions = new HashMap<>();
    private final Map<String, Set<Operation>> allOperations = new HashMap<>();

    public TeleportService(BotCore botCore, GameConfig gameConfig) {
        this.botCore = botCore;
        this.gameConfig = gameConfig;
    }

    @Override
    public Event updateHandler(Event event) {
        String sessionId = event.getSessionId();

        addOperations(sessionId, event.getActor());
        addOperations(sessionId, event.getOperation().getTarget());
        botCore.addOperations(sessionId, allOperations.get(sessionId));

        return botCore.eventHandler(event);
    }

    private void addOperations(String sessionId, Actor actor) {
        if (!sessions.get(sessionId).contains(actor)) {
            sessions.get(sessionId).add(actor);
            gameConfig.getActions().forEach(action -> allOperations.get(sessionId).add(Operation.of(action, actor)));
        }
    }

    @Override
    public Operation executeOperation() {
        return botCore.executeOperation();
    }

    @Override
    public void initSession(String sessionId) {
        sessions.put(sessionId, new HashSet<>());
        allOperations.put(sessionId, new HashSet<>());
    }

    @Override
    public boolean isInitSession(String sessionId) {
        return sessions.containsKey(sessionId);
    }

    @Override
    public void addBot(String sessionId, Actor actor) {
        addOperations(sessionId, actor);
        botCore.addOperations(sessionId, allOperations.get(sessionId));

        botCore.addScheduler(sessionId, actor);
    }

}
