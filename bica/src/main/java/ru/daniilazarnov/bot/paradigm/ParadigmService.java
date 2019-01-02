package ru.daniilazarnov.bot.paradigm;

import ru.daniilazarnov.common.model.Event;
import ru.daniilazarnov.common.model.Operation;

public interface ParadigmService {

    Event eventHandle(Event eventTO);

    Operation executeOperation();

    String getUrl();

}
