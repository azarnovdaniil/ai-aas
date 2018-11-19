package ru.daniilazarnov.bot.transport.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.daniilazarnov.bot.paradigm.BotService;
import ru.daniilazarnov.bot.transport.dao.EventDao;
import ru.daniilazarnov.bot.transport.dto.EventTO;

import java.util.List;
import java.util.stream.Collectors;

import static ru.daniilazarnov.bot.transport.converter.EventConverter.event_eventTO;

@RestController
public class BotController {

    private final BotService botService;
    private final EventDao eventDao;

    public BotController(BotService botService, EventDao eventDao) {
        this.botService = botService;
        this.eventDao = eventDao;
    }

    @PostMapping("/shooter")
    public EventTO shooterController(@RequestBody EventTO eventTO) {
        return botService.eventHandle(eventTO);
    }

    @PostMapping("/teleport")
    public EventTO teleportController(@RequestBody EventTO eventTO) {
        return botService.eventHandle(eventTO);
    }

    @PostMapping("/telegram")
    public EventTO telegramController(@RequestBody EventTO eventTO) {
        return botService.eventHandle(eventTO);
    }

    @PostMapping("/client")
    public EventTO clientController(@RequestBody EventTO eventTO) {
        return botService.eventHandle(eventTO);
    }

    @RequestMapping("/emulation")
    public List<EventTO> teleportController() {
        return eventDao.getListEvent().stream()
                .map(event_eventTO)
                .map(botService::eventHandle)
                .collect(Collectors.toList());
    }

}
