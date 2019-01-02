package ru.daniilazarnov.bot.paradigm.teleport;

import org.springframework.stereotype.Service;
import ru.daniilazarnov.bot.core.BotCore;
import ru.daniilazarnov.bot.paradigm.ParadigmService;
import ru.daniilazarnov.bot.property.BotProperties;
import ru.daniilazarnov.common.config.GameConfig;
import ru.daniilazarnov.common.model.Actor;
import ru.daniilazarnov.common.model.Event;
import ru.daniilazarnov.common.model.Operation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class TeleportService implements ParadigmService {

    private final BotCore botCore;
    private final BotProperties properties;
    private final GameConfig gameConfig;
    private final Map<String, Set<Actor>> sessions = new HashMap<>();
    private final Map<String, Set<Operation>> allOperations = new HashMap<>();

    public TeleportService(BotCore botCore, BotProperties properties, GameConfig gameConfig) {
        this.botCore = botCore;
        this.properties = properties;
        this.gameConfig = gameConfig;
    }

    @Override
    public void eventHandle(Event event) {
        String sessionId = event.getSessionId();

        addOperations(sessionId, event.getActor());
        addOperations(sessionId, event.getTarget());
        botCore.addOperations(sessionId, allOperations.get(sessionId));

        botCore.actionHandle(event);
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
    public String getUrl() {
        return properties.getClientUrl();
    }

    @Override
    public void addSession(String sessionId) {
        sessions.put(sessionId, new HashSet<>());
        allOperations.put(sessionId, new HashSet<>());
    }

    @Override
    public boolean isInitSession(String sessionId) {
        return sessions.containsKey(sessionId);
    }

}
