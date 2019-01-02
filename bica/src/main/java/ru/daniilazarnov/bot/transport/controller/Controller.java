package ru.daniilazarnov.bot.transport.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.daniilazarnov.bot.paradigm.ParadigmService;
import ru.daniilazarnov.common.model.Event;

@RestController
public class Controller {

    private final ParadigmService paradigmService;

    public Controller(ParadigmService paradigmService) {
        this.paradigmService = paradigmService;
    }

    @PostMapping("/teleport")
    public void teleportController(@RequestBody Event event) {
        paradigmService.eventHandle(event);
    }

}
