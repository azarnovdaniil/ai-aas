package ru.daniilazarnov.bot.transport.client;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.daniilazarnov.bot.paradigm.ParadigmService;
import ru.daniilazarnov.common.model.Operation;

import java.time.Duration;

@Service
public class Client {

    private final ParadigmService paradigmService;
    private final TaskScheduler taskScheduler = new ConcurrentTaskScheduler();
    private final RestTemplate restTemplate = new RestTemplate();

    public Client(ParadigmService paradigmService) {
        this.paradigmService = paradigmService;
        initExecutor();
    }

    private void initExecutor() {
        taskScheduler.scheduleWithFixedDelay(() -> {
            Operation operation = paradigmService.executeOperation();
            String url = paradigmService.getUrl();

            restTemplate.postForObject(url, operation, Operation.class);
        }, Duration.ofMillis(20));
    }

}
