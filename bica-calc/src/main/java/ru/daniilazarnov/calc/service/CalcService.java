package ru.daniilazarnov.calc.service;

import ru.daniilazarnov.calc.domain.Event;
import ru.daniilazarnov.calc.domain.UnityLogRow;

public interface CalcService {

    Event calculateAppraisal(UnityLogRow unityLogRow);

}
