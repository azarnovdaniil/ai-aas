package ru.daniilazarnov.bot.transport.client;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.daniilazarnov.bot.paradigm.ParadigmService;
import ru.daniilazarnov.common.model.Actor;
import ru.daniilazarnov.common.model.Operation;

import java.time.Duration;

@Service
public class Client {

    private final ParadigmService paradigmService;
    private final RestTemplate restTemplate = new RestTemplate();

    public Client(ParadigmService paradigmService) {
        this.paradigmService = paradigmService;
    }

    public void initBot(String sessionId, Actor actor) {
        TaskScheduler taskScheduler = new ConcurrentTaskScheduler();

        paradigmService.addBot(sessionId, actor);

        taskScheduler.scheduleWithFixedDelay(() -> {
            Operation operation = paradigmService.executeOperation();

            if (!operation.equals(Operation.none())) {
                String url = paradigmService.getUrl() + "?sessionId=" + sessionId + "&botName=" + actor.getName();

                restTemplate.postForObject(url, operation, Operation.class);
            }
        }, Duration.ofSeconds(2));
    }

}
