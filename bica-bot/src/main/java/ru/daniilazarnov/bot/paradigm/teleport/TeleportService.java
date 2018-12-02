package ru.daniilazarnov.bot.paradigm.teleport;

import org.springframework.stereotype.Service;
import ru.daniilazarnov.bot.core.BotCore;
import ru.daniilazarnov.bot.core.domain.Operation;
import ru.daniilazarnov.bot.paradigm.BotService;
import ru.daniilazarnov.bot.paradigm.teleport.converter.ActionConverter;
import ru.daniilazarnov.bot.paradigm.teleport.property.TeleportProperties;
import ru.daniilazarnov.common.model.Action;
import ru.daniilazarnov.common.model.Actor;
import ru.daniilazarnov.common.model.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeleportService implements BotService {

    private final BotCore botCore;
    private final TeleportProperties teleportProperties;
    private final Map<String, Action> actions = new HashMap<>();
    private final Map<String, Actor> actors = new HashMap<>();
    private final Actor mainActor = Actor.valueOf("Bot");
    private final List<Operation> operations = new ArrayList<>();

    public TeleportService(BotCore botCore, TeleportProperties teleportProperties) {
        this.botCore = botCore;
        this.teleportProperties = teleportProperties;
        init();
    }

    private void init() {
        for (int i = 0; i < teleportProperties.getCountPlayer(); i++) {
            actors.put("Actor" + i, Actor.valueOf("Actor" + i));
        }

        botCore.initMemory(actors.values());

        teleportProperties.getActions().forEach(action -> actions.put(action.getName(), ActionConverter.action_action.apply(action)));

        actions.values().forEach(action -> actors.values().forEach(actor -> operations.add(new Operation(action, mainActor, actor))));
    }

    @Override
    public Event eventHandle(Event event) {

        Operation operation = botCore.actionHandle(operations);

        botCore.updateMemory(actions.get(event.getAction().getActionName()), actors.get(event.getActor().getName()));

//        event.setActionName(operation.getAction().getActionName());
//        event.setActorName(operation.getActor().getName());
//        event.setTargetName(operation.getTarget().getName());

        return event;
    }

}
