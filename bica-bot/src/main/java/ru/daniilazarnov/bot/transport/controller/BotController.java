package ru.daniilazarnov.bot.transport.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.daniilazarnov.bot.paradigm.BotService;
import ru.daniilazarnov.common.model.Event;

@RestController
public class BotController {

    private final BotService botService;

    public BotController(BotService botService) {
        this.botService = botService;
    }

    @PostMapping("/shooter")
    public Event shooterController(@RequestBody Event event) {
        return botService.eventHandle(event);
    }

    @PostMapping("/teleport")
    public Event teleportController(@RequestBody Event event) {
        return botService.eventHandle(event);
    }

    @PostMapping("/telegram")
    public Event telegramController(@RequestBody Event event) {
        return botService.eventHandle(event);
    }

    @PostMapping("/client")
    public Event clientController(@RequestBody Event event) {
        return botService.eventHandle(event);
    }

}
