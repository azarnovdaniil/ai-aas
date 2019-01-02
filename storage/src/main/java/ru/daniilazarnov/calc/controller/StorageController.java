package ru.daniilazarnov.calc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.daniilazarnov.bot.core.service.MemoryService;
import ru.daniilazarnov.calc.storage.LogDao;
import ru.daniilazarnov.calc.storage.serialization.Serializer;
import ru.daniilazarnov.calc.storage.system.StorageService;
import ru.daniilazarnov.common.model.Event;
import ru.daniilazarnov.common.model.Game;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StorageController {

    private final MemoryService memoryService;
    private final StorageService storageService;
    private final Serializer serializer;
    private final LogDao logDao;

    private static final Logger logger = LoggerFactory.getLogger(StorageController.class);

    public StorageController(MemoryService memoryService, LogDao logDao,
                             StorageService storageService, Serializer serializer) {

        this.memoryService = memoryService;
        this.storageService = storageService;
        this.logDao = logDao;
        this.serializer = serializer;
    }

    @RequestMapping("/calculate")
    public void csvController() {

        storageService.loadAll().forEach(path -> {
            List<String> collect = logDao.getListEvent(path)
                    .stream()
                    .sorted(Comparator.comparing(Event::getLocalDateTime))
                    .peek(memoryService::updateMemory)
                    .map(serializer::toCSV)
                    .collect(Collectors.toList());

            try {
                storageService.writeCsv(path, collect);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        });
    }

    @RequestMapping("/json")
    public void jsonController() {

        storageService.loadAll().forEach(path -> {
            List<Event> events = logDao.getListEvent(path)
                    .stream()
                    .sorted(Comparator.comparing(Event::getLocalDateTime))
                    .peek(memoryService::updateMemory)
                    .collect(Collectors.toList());

            storageService.writeJson(path, serializer.toJSON(Game.of(events)));
        });

    }

}
