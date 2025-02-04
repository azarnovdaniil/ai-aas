package ru.daniilazarnov.bot.transport.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.daniilazarnov.bot.paradigm.ParadigmService;
import ru.daniilazarnov.bot.property.ParadigmProperties;
import ru.daniilazarnov.bot.transport.converter.OperationConverter;
import ru.daniilazarnov.common.model.dto.OperationTO;
import ru.daniilazarnov.common.model.entity.Actor;
import ru.daniilazarnov.common.model.entity.Operation;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
public class ParadigmClient {

    private static final Logger logger = LoggerFactory.getLogger(ParadigmClient.class);

    private final ParadigmService paradigmService;
    private final ParadigmProperties paradigmProperties;
    private final OperationConverter operationConverter;
    private final RestTemplate restTemplate = new RestTemplate();
    private final Map<String, Map<Actor, TaskScheduler>> taskSchedulers = new HashMap<>();

    public ParadigmClient(ParadigmService paradigmService, ParadigmProperties paradigmProperties, OperationConverter operationConverter) {
        this.paradigmService = paradigmService;
        this.paradigmProperties = paradigmProperties;
        this.operationConverter = operationConverter;
    }

    public void initBot(String sessionId, Actor actor) {
        taskSchedulers
                .computeIfAbsent(sessionId, key -> new HashMap<>())
                .computeIfAbsent(actor, key -> scheduler(sessionId, actor));

        paradigmService.addBot(sessionId, actor);
    }

    private TaskScheduler scheduler(String sessionId, Actor actor) {
        logger.info("Adding new bot client scheduler " + sessionId + " " + actor);
        TaskScheduler taskScheduler = new ConcurrentTaskScheduler();

        taskScheduler.scheduleWithFixedDelay(() -> {
            Operation executeOperation = paradigmService.executeOperation();

            if (executeOperation.equals(Operation.none())) {
                OperationTO operation = operationConverter.convert(executeOperation);

                String url = paradigmProperties.getUrl() + "?sessionId=" + sessionId + "&botName=" + actor.getName();

                restTemplate.postForObject(url, operation, OperationTO.class);
            }
        }, Duration.ofSeconds(2));

        return taskScheduler;
    }

}
