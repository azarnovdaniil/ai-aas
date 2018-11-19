package ru.daniilazarnov.calc.service;

import org.springframework.stereotype.Service;
import ru.daniilazarnov.calc.dao.MemoryDao;
import ru.daniilazarnov.calc.domain.Event;
import ru.daniilazarnov.calc.domain.UnityLogRow;

@Service
public class CalServiceImpl implements CalcService {

    private final MemoryDao memoryDao;

    public CalServiceImpl(MemoryDao memoryDao) {
        this.memoryDao = memoryDao;
    }

    @Override
    public Event calculateAppraisal(UnityLogRow unityLogRow) {
        memoryDao.updateMemory(unityLogRow);

        return new Event(unityLogRow, memoryDao.getAppraisalState(unityLogRow.getSessionId()));
    }
}
