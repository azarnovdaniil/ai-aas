package ru.daniilazarnov.bot.paradigm.teleport;

import org.springframework.stereotype.Service;
import ru.daniilazarnov.bot.core.BotCore;
import ru.daniilazarnov.bot.paradigm.ParadigmService;
import ru.daniilazarnov.bot.property.BotProperties;
import ru.daniilazarnov.common.config.GameConfig;
import ru.daniilazarnov.common.model.Action;
import ru.daniilazarnov.common.model.Event;
import ru.daniilazarnov.common.model.Operation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TeleportService implements ParadigmService {

    private final BotCore botCore;
    private final BotProperties properties;
    private final List<Action> actions;
    private final List<String> systemActions;
    private final Set<String> sessions = new HashSet<>();

    public TeleportService(BotCore botCore, BotProperties properties, GameConfig gameConfig) {
        this.botCore = botCore;
        this.properties = properties;
        actions = gameConfig.getActions();
        systemActions = gameConfig.getSystemActions();
    }

    @Override
    public void eventHandle(Event event) {
        botCore.actionHandle(event);
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
    public boolean addSession(String sessionId) {
        return sessions.add(sessionId);
    }

}
