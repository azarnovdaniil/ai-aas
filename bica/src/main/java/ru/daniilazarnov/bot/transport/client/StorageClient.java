package ru.daniilazarnov.bot.transport.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.daniilazarnov.common.model.data.Event;

@Service
public class StorageClient {

    public void saveIntoStorage(Event event) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://localhost:8082/storage";
        restTemplate.postForObject(resourceUrl, event, Event.class);
    }
}
