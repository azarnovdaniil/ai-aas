package ru.daniilazarnov.calc.service;

import org.springframework.stereotype.Service;
import ru.daniilazarnov.calc.botmemory.BotService;
import ru.daniilazarnov.common.model.Event;

@Service
public class CalServiceImpl implements CalcService {

    private final BotService botService;

    public CalServiceImpl(BotService botService) {
        this.botService = botService;
    }

    @Override
    public Event calculateAppraisal(Event event) {
        return botService.updateMemory(event);
    }
}
