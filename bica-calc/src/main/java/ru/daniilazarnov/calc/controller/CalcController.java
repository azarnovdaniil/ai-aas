package ru.daniilazarnov.calc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.daniilazarnov.calc.dao.LogDao;
import ru.daniilazarnov.calc.domain.*;
import ru.daniilazarnov.calc.serialization.csv.CsvEventSerializer;
import ru.daniilazarnov.calc.serialization.json.*;
import ru.daniilazarnov.calc.service.CalcService;
import ru.daniilazarnov.calc.service.StorageService;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CalcController {

    private final CsvEventSerializer csvEventSerializer;
    private final CalcService calcService;
    private final StorageService storageService;
    private final LogDao logDao;

    private static final Logger logger = LoggerFactory.getLogger(CalcController.class);

    public CalcController(CalcService calcService, LogDao logDao, StorageService storageService, CsvEventSerializer csvEventSerializer) {
        this.csvEventSerializer = csvEventSerializer;
        this.calcService = calcService;
        this.storageService = storageService;
        this.logDao = logDao;
    }

    @RequestMapping("/calculate")
    public void csvController() {

        storageService.loadAll().forEach(path -> {
            List<String> collect = logDao.getListEvent(path)
                    .stream()
                    .sorted(Comparator.comparing(Event::getLocalDateTime))
                    .peek(calcService::calculateAppraisal)
                    .map(csvEventSerializer::serialize)
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

        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(Game.class, new GameSerializer());
        module.addSerializer(Event.class, new EventSerializer());
        module.addSerializer(Action.class, new ActionSerializer());
        module.addSerializer(Actor.class, new ActorSerializer());
        module.addSerializer(Appraisal.class, new AppraisalSerializer());
        module.addSerializer(State.class, new StateSerializer());

        mapper.registerModule(module);

        storageService.loadAll().forEach(path -> {
            List<Event> events = logDao.getListEvent(path)
                    .stream()
                    .sorted(Comparator.comparing(Event::getLocalDateTime))
                    .peek(calcService::calculateAppraisal)
                    .collect(Collectors.toList());

            Game game = new Game(events);

            try {
                storageService.writeJson(path, mapper.writeValueAsString(game));
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        });

    }
}
