package ru.daniilazarnov.calc.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.daniilazarnov.calc.data.EventRepository;
import ru.daniilazarnov.calc.property.ParserProperties;
import ru.daniilazarnov.calc.storage.LogDao;
import ru.daniilazarnov.calc.storage.serialization.Serializer;
import ru.daniilazarnov.calc.storage.system.StorageService;
import ru.daniilazarnov.common.model.data.Event;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StorageController {

    private final EventRepository eventRepository;
    private final ParserProperties parserProperties;
    private final StorageService storageService;
    private final Serializer serializer;
    private final LogDao logDao;
    private final RestTemplate restTemplate = new RestTemplate();

    private static final Logger logger = LoggerFactory.getLogger(StorageController.class);

    public StorageController(EventRepository eventRepository, ParserProperties parserProperties, LogDao logDao,
                             StorageService storageService, Serializer serializer) {
        this.eventRepository = eventRepository;

        this.parserProperties = parserProperties;
        this.storageService = storageService;
        this.logDao = logDao;
        this.serializer = serializer;
    }

    @PostMapping("/storage")
    public void saveStorage(@RequestBody Event event) {
        eventRepository.save(event);
    }

    @RequestMapping("/print")
    public void print() {
        eventRepository.findAll().forEach(event -> logger.info(serializer.toCSV(event)));
    }

    @RequestMapping("/calculate")
    public void csvController() {
        logger.info("Call calculate");

        storageService.loadAll().forEach(path -> {
            List<String> collect = logDao.getListEvent(path)
                    .stream()
                    .sorted(Comparator.comparing(Event::getZonedDateTime))
                    .peek(this::client)
                    .map(serializer::toCSV)
                    .collect(Collectors.toList());

            try {
                storageService.writeCsv(path, collect);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        });
    }

    private Event client(Event event) {
        String url = parserProperties.getBotUrl();
        return restTemplate.postForObject(url, event, Event.class);
    }

}
