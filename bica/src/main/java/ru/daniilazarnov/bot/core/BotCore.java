package ru.daniilazarnov.bot.core;

import ru.daniilazarnov.common.model.Event;
import ru.daniilazarnov.common.model.Operation;

public interface BotCore {

    void actionHandle(Event event);

    Operation executeOperation();

}
