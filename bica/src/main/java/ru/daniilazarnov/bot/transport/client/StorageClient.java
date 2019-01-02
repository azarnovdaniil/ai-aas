package ru.daniilazarnov.bot.transport.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.daniilazarnov.bot.property.BotProperties;
import ru.daniilazarnov.common.model.data.Event;

@Service
public class StorageClient {

    private final BotProperties botProperties;
    private final RestTemplate restTemplate = new RestTemplate();

    public StorageClient(BotProperties botProperties) {
        this.botProperties = botProperties;
    }

    public void sendIntoStorage(Event event) {
        String url = botProperties.getStorageUrl();
        restTemplate.postForObject(url, event, Event.class);
    }
}
