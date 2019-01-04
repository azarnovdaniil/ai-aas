package ru.daniilazarnov.bot.transport.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.daniilazarnov.bot.property.StorageProperties;
import ru.daniilazarnov.common.model.entity.Event;

@Service
public class StorageClient {

    private final StorageProperties storageProperties;
    private final RestTemplate restTemplate = new RestTemplate();

    public StorageClient(StorageProperties storageProperties) {
        this.storageProperties = storageProperties;
    }

    public void sendIntoStorage(Event event) {
        String url = storageProperties.getUrl();
        restTemplate.postForObject(url, event, Event.class);
    }
}
