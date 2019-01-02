package ru.daniilazarnov.bot.transport.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.daniilazarnov.bot.paradigm.ParadigmService;
import ru.daniilazarnov.bot.transport.client.Client;
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
        if (paradigmService.addSession(event.getSessionId())) {
            client.initExecutor();
        }

        paradigmService.eventHandle(event);
    }

}
