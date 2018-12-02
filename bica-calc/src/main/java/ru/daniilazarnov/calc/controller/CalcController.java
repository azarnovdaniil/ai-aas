package ru.daniilazarnov.calc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.daniilazarnov.calc.dao.LogDao;
import ru.daniilazarnov.calc.serialization.Serializer;
import ru.daniilazarnov.calc.service.CalcService;
import ru.daniilazarnov.calc.service.StorageService;
import ru.daniilazarnov.common.model.Event;
import ru.daniilazarnov.common.model.Game;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CalcController {

    private final CalcService calcService;
    private final StorageService storageService;
    private final Serializer serializer;
    private final LogDao logDao;

    private static final Logger logger = LoggerFactory.getLogger(CalcController.class);

    public CalcController(CalcService calcService, LogDao logDao,
                          StorageService storageService, Serializer serializer) {

        this.calcService = calcService;
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
                    .peek(calcService::calculateAppraisal)
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
                    .peek(calcService::calculateAppraisal)
                    .collect(Collectors.toList());

            Game game = new Game(events);

            try {
                storageService.writeJson(path, serializer.toJSON(game));
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        });

    }

    @PostMapping("/cli")
    public String cliController() throws JsonProcessingException {

        Path path1 = storageService.loadAll().findFirst().get();

        List<Event> events = logDao.getListEvent(path1)
                .stream()
                .sorted(Comparator.comparing(Event::getLocalDateTime))
                .peek(calcService::calculateAppraisal)
                .collect(Collectors.toList());

        Game game = new Game(events);

        return serializer.toJSON(game);
    }
}
