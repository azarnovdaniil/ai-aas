package ru.daniilazarnov.bot.transport.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.daniilazarnov.bot.paradigm.ParadigmService;
import ru.daniilazarnov.bot.transport.client.ParadigmClient;
import ru.daniilazarnov.bot.transport.client.StorageClient;
import ru.daniilazarnov.bot.transport.converter.EventConverter;
import ru.daniilazarnov.common.model.dto.EventTO;
import ru.daniilazarnov.common.model.entity.Actor;
import ru.daniilazarnov.common.model.entity.Event;

@RestController
public class Controller {

    private final ParadigmService paradigmService;
    private final ParadigmClient paradigmClient;
    private final StorageClient storageClient;
    private final EventConverter eventConverter;

    public Controller(ParadigmService paradigmService, ParadigmClient paradigmClient, StorageClient storageClient, EventConverter eventConverter) {
        this.paradigmService = paradigmService;
        this.paradigmClient = paradigmClient;
        this.storageClient = storageClient;
        this.eventConverter = eventConverter;
    }

    @PostMapping("/calculate")
    public Event calculateHandler(@RequestBody Event event) {
        String sessionId = event.getSessionId();
        if (!paradigmService.isInitSession(sessionId)) {
            paradigmService.initSession(sessionId);
        }

        Event updatedEvent = paradigmService.updateHandler(event);
        storageClient.sendIntoStorage(updatedEvent);

        return updatedEvent;
    }

    @PostMapping("/update")
    public void updateHandler(@RequestBody EventTO eventTO) {
        String sessionId = eventTO.getSessionId();
        if (!paradigmService.isInitSession(sessionId)) {
            paradigmService.initSession(sessionId);
        }
        Event event = eventConverter.convert(eventTO);

        storageClient.sendIntoStorage(paradigmService.updateHandler(event));
    }

    @PostMapping(value = "/update/init", params = {"sessionId", "botName"})
    public void initHandler(@RequestParam("sessionId") String sessionId, @RequestParam("botName") String botName) {
        if (!paradigmService.isInitSession(sessionId)) {
            paradigmService.initSession(sessionId);
            paradigmClient.initBot(sessionId, Actor.valueOf(botName));
        } else {
            paradigmClient.initBot(sessionId, Actor.valueOf(botName));
        }
    }

}
