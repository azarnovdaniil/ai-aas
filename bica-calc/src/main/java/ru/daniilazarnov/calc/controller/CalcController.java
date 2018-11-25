package ru.daniilazarnov.calc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.daniilazarnov.calc.dao.LogDao;
import ru.daniilazarnov.calc.service.CalcService;
import ru.daniilazarnov.calc.service.StorageService;

import static java.lang.System.out;

@RestController
public class CalcController {

    private final CalcService calcService;
    private final LogDao logDao;
    private final StorageService storageService;

    public CalcController(CalcService calcService, LogDao logDao, StorageService storageService) {
        this.calcService = calcService;
        this.logDao = logDao;
        this.storageService = storageService;
    }

    @RequestMapping("/calculate")
    public void teleportController() {
        logDao.getListEvent().stream()
                .map(calcService::calculateAppraisal)
                .forEach(event -> out.println(event.toCSV()));

    }
}
