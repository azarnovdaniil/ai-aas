package ru.daniilazarnov.bot.core.emotional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Service;
import ru.daniilazarnov.common.model.data.Actor;
import ru.daniilazarnov.common.model.data.Operation;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

@Service
public class OperationServiceImpl implements OperationService {

    private final EmotionalActionService emotionalActionService;
    private final BlockingDeque<Operation> operations = new LinkedBlockingDeque<>();
    private final Map<String, Map<Actor, TaskScheduler>> taskSchedulers = new HashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(OperationServiceImpl.class);

    public OperationServiceImpl(EmotionalActionService emotionalActionService) {
        this.emotionalActionService = emotionalActionService;
    }

    @Override
    public void addScheduler(String sessionId, Actor actor) {
        taskSchedulers
                .computeIfAbsent(sessionId, key -> new HashMap<>())
                .computeIfAbsent(actor, key -> scheduler(sessionId, actor));
    }

    private TaskScheduler scheduler(String sessionId, Actor actor) {
        logger.info("Adding new bot operation scheduler " + sessionId + " " + actor);
        TaskScheduler taskScheduler = new ConcurrentTaskScheduler();

        taskScheduler.scheduleWithFixedDelay(() -> {
            Operation operation = emotionalActionService.chooseOperation(sessionId, actor);
            operations.push(operation);
            logger.info("Chosen new operation " + operation + " for " + actor + " into " + sessionId);
        }, Duration.ofSeconds(1));

        logger.info("Added new bot operation scheduler" + sessionId + " " + actor);
        return taskScheduler;
    }

    @Override
    public Operation getOperation() {
        if (!operations.isEmpty()) {
            return operations.pop();
        } else {
            return Operation.none();
        }
    }
}
