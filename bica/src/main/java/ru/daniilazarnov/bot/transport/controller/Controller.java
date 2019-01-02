package ru.daniilazarnov.bot.transport.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.daniilazarnov.bot.paradigm.ParadigmService;
import ru.daniilazarnov.bot.transport.client.ParadigmClient;
import ru.daniilazarnov.bot.transport.client.StorageClient;
import ru.daniilazarnov.common.model.data.Actor;
import ru.daniilazarnov.common.model.data.Event;

@RestController
public class Controller {

    private final ParadigmService paradigmService;
    private final ParadigmClient paradigmClient;
    private final StorageClient storageClient;

    public Controller(ParadigmService paradigmService, ParadigmClient paradigmClient, StorageClient storageClient) {
        this.paradigmService = paradigmService;
        this.paradigmClient = paradigmClient;
        this.storageClient = storageClient;
    }

    @PostMapping("/teleport")
    public void teleportController(@RequestBody Event event) {
        String sessionId = event.getSessionId();
        if (!paradigmService.isInitSession(sessionId)) {
            paradigmService.initSession(sessionId);
        }

        storageClient.sendIntoStorage(paradigmService.eventHandle(event));
    }

    @PostMapping(value = "/teleport/init", params = {"sessionId", "botName"})
    public void teleportInitController(@RequestParam("sessionId") String sessionId, @RequestParam("botName") String botName) {
        if (!paradigmService.isInitSession(sessionId)) {
            paradigmService.initSession(sessionId);
            paradigmClient.initBot(sessionId, Actor.valueOf(botName));
        } else {
            paradigmClient.initBot(sessionId, Actor.valueOf(botName));
        }
    }

}
