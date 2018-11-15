package ru.daniilazarnov.bot.paradigm;

import ru.daniilazarnov.bot.transport.dto.EventTO;

public interface BotService {

    EventTO eventHandle(EventTO eventTO);
}
