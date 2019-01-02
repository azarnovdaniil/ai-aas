package ru.daniilazarnov.bot.core;

import org.springframework.stereotype.Service;
import ru.daniilazarnov.bot.core.emotional.OperationService;
import ru.daniilazarnov.bot.core.rational.RationalService;
import ru.daniilazarnov.bot.core.service.MemoryService;
import ru.daniilazarnov.common.model.Event;
import ru.daniilazarnov.common.model.Operation;

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
    public void actionHandle(Event event) {
        botService.updateMemory(event);
        operationService.addScheduler(event.getSessionId(), event.getActor());
    }

    @Override
    public void addOperations(String sessionId, Set<Operation> operations) {
        rationalService.addOperations(sessionId, operations);
    }

    @Override
    public Operation executeOperation() {
        return operationService.getOperation();
    }
}
