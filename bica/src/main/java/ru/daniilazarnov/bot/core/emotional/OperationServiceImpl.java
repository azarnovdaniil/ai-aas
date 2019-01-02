package ru.daniilazarnov.bot.core.emotional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Service;
import ru.daniilazarnov.common.model.Actor;
import ru.daniilazarnov.common.model.Operation;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

@Service
public class OperationServiceImpl implements OperationService {

    private final EmotionalActionService emotionalActionService;
    private final Map<Actor, TaskScheduler> schedulerMap = new ConcurrentHashMap<>();
    private final BlockingDeque<Operation> operations = new LinkedBlockingDeque<>();

    private static final Logger logger = LoggerFactory.getLogger(OperationServiceImpl.class);

    public OperationServiceImpl(EmotionalActionService emotionalActionService) {
        this.emotionalActionService = emotionalActionService;
    }

    @Override
    public void addScheduler(String sessionId, Actor actor) {
        TaskScheduler taskScheduler = new ConcurrentTaskScheduler();

        taskScheduler.scheduleWithFixedDelay(() -> {
            Operation operation = emotionalActionService.chooseOperation(sessionId, actor);
            operations.push(operation);
            logger.info("Choose new operation");
        }, Duration.ofSeconds(1));

        schedulerMap.put(actor, taskScheduler);
    }

    @Override
    public Operation getOperation() {
        if (!operations.isEmpty()) {
            return operations.pop();
        } else {
            return Operation.builder().build();
        }
    }
}
