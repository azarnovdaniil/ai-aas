package ru.daniilazarnov.bot.transport.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.daniilazarnov.bot.paradigm.ParadigmService;
import ru.daniilazarnov.bot.transport.client.Client;
import ru.daniilazarnov.common.model.Actor;
import ru.daniilazarnov.common.model.Event;

@RestController
public class Controller {

    private final ParadigmService paradigmService;
    private final Client client;

    public Controller(ParadigmService paradigmService, Client client) {
        this.paradigmService = paradigmService;
        this.client = client;
    }

    @PostMapping("/teleport")
    public void teleportController(@RequestBody Event event) {
        String sessionId = event.getSessionId();
        if (!paradigmService.isInitSession(sessionId)) {
            paradigmService.initSession(sessionId);
        }

        paradigmService.eventHandle(event);
    }

    @PostMapping(value = "/teleport/init", params = {"sessionId", "botName"})
    public void teleportInitController(@RequestParam("sessionId") String sessionId, @RequestParam("botName") String botName) {
        if (!paradigmService.isInitSession(sessionId)) {
            paradigmService.initSession(sessionId);
            client.initBot(sessionId, Actor.valueOf(botName));
        } else {
            client.initBot(sessionId, Actor.valueOf(botName));
        }
    }

}
