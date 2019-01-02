package ru.daniilazarnov.bot.core.emotional;

import ru.daniilazarnov.common.model.data.Actor;
import ru.daniilazarnov.common.model.data.Operation;

public interface EmotionalActionService {

    Operation chooseOperation(String sessionId, Actor actor);

}
