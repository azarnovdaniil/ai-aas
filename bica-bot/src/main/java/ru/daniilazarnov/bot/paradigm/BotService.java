package ru.daniilazarnov.bot.paradigm;

import ru.daniilazarnov.common.model.Event;

public interface BotService {

    Event eventHandle(Event eventTO);

}
