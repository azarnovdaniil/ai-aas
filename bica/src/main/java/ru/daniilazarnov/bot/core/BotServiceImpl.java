package ru.daniilazarnov.bot.core;

import org.springframework.stereotype.Service;
import ru.daniilazarnov.bot.core.emotional.OperationService;
import ru.daniilazarnov.bot.core.rational.RationalService;
import ru.daniilazarnov.bot.core.service.MemoryService;
import ru.daniilazarnov.common.model.entity.Actor;
import ru.daniilazarnov.common.model.entity.Event;
import ru.daniilazarnov.common.model.entity.Operation;

import java.util.Set;

@Service
public class BotServiceImpl implements BotCore {

    private final MemoryService botService;
    private final RationalService rationalService;
    private final OperationService operationService;

    public BotServiceImpl(MemoryService botService, RationalService rationalService, OperationService operationService) {
        this.botService = botService;
        this.rationalService = rationalService;
        this.operationService = operationService;
    }

    @Override
    public Event eventHandler(Event event) {
        return botService.updateMemory(event);
    }

    @Override
    public void addOperations(String sessionId, Set<Operation> operations) {
        rationalService.addOperations(sessionId, operations);
    }

    @Override
    public void addScheduler(String sessionId, Actor actor) {
        operationService.addScheduler(sessionId, actor);
    }

    @Override
    public Operation executeOperation() {
        return operationService.getOperation();
    }
}
