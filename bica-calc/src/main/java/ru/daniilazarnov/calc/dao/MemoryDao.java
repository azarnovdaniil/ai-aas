package ru.daniilazarnov.calc.dao;

import ru.daniilazarnov.calc.domain.UnityLogRow;

public interface MemoryDao {

    void updateMemory(UnityLogRow unityLogRow);

    String getAppraisalState(String sessionId);

}
