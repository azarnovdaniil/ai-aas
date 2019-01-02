package ru.daniilazarnov.bot.transport.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.daniilazarnov.bot.paradigm.ParadigmService;
import ru.daniilazarnov.common.model.Actor;
import ru.daniilazarnov.common.model.Operation;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
public class Client {

    private static final Logger logger = LoggerFactory.getLogger(Client.class);

    private final ParadigmService paradigmService;
    private final RestTemplate restTemplate = new RestTemplate();
    private final Map<String, Map<Actor, TaskScheduler>> taskSchedulers = new HashMap<>();

    public Client(ParadigmService paradigmService) {
        this.paradigmService = paradigmService;
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
            Operation operation = paradigmService.executeOperation();

            if (!operation.equals(Operation.none())) {
                String url = paradigmService.getUrl() + "?sessionId=" + sessionId + "&botName=" + actor.getName();

                restTemplate.postForObject(url, operation, Operation.class);
            }
        }, Duration.ofSeconds(2));

        return taskScheduler;
    }

}
