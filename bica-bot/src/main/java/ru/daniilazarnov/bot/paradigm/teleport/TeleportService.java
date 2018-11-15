package ru.daniilazarnov.bot.paradigm.teleport;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.daniilazarnov.bot.core.BotCore;
import ru.daniilazarnov.bot.paradigm.teleport.config.TeleportConfig;
import ru.daniilazarnov.bot.paradigm.teleport.converter.ActionConverter;
import ru.daniilazarnov.bot.transport.dto.EventTO;
import ru.daniilazarnov.bot.core.domain.Action;
import ru.daniilazarnov.bot.core.domain.Actor;
import ru.daniilazarnov.bot.core.domain.Operation;
import ru.daniilazarnov.bot.paradigm.BotService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.daniilazarnov.bot.paradigm.profile.ProfileParadigm.TELEPORT;

@Service
@Profile(TELEPORT)
public class TeleportService implements BotService {

    private final BotCore botCore;
    private final TeleportConfig teleportConfig;
    private final Map<String, Action> actions = new HashMap<>();
    private final Map<String, Actor> actors = new HashMap<>();
    private final Actor mainActor = new Actor("Bot");
    private final List<Operation> operations = new ArrayList<>();

    public TeleportService(BotCore botCore, TeleportConfig teleportConfig) {
        this.botCore = botCore;
        this.teleportConfig = teleportConfig;
        init();
    }

    private void init() {
        for (int i = 0; i < teleportConfig.getCountPlayer(); i++) {
            actors.put("Actor" + i, new Actor("Actor" + i));
        }

        botCore.initMemory(actors.values());

        teleportConfig.getActions().forEach(action -> actions.put(action.getName(), ActionConverter.action_action.apply(action)));

        actions.values().forEach(action -> actors.values().forEach(actor -> operations.add(new Operation(action, mainActor, actor))));
    }

    @Override
    public EventTO eventHandle(EventTO eventTO) {
        Operation operation = botCore.actionHandle(operations);

        botCore.updateMemory(actions.get(eventTO.getActionName()), actors.get(eventTO.getActorName()));

        eventTO.setActionName(operation.getAction().getActionName());
        eventTO.setActorName(operation.getActor().getName());
        eventTO.setTargetName(operation.getTarget().getName());

        return eventTO;
    }

}
