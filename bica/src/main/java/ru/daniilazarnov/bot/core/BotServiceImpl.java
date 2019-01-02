package ru.daniilazarnov.bot.core;

import org.springframework.stereotype.Service;
import ru.daniilazarnov.bot.core.service.MemoryService;
import ru.daniilazarnov.bot.core.emotional.OperationService;
import ru.daniilazarnov.bot.core.rational.RationalService;
import ru.daniilazarnov.common.model.Event;
import ru.daniilazarnov.common.model.Operation;

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
    public Event actionHandle(Event event) {
        Operation operation = Operation.builder()
                .setAction(event.getAction())
                .setTarget(event.getTarget())
                .build();

        rationalService.addOperation(event.getSessionId(), event.getActor(), operation);

        operationService.addScheduler(event.getSessionId(), event.getActor());

        return botService.updateMemory(event);
    }

    @Override
    public Operation executeOperation() {
        return operationService.getOperation();
    }
}
