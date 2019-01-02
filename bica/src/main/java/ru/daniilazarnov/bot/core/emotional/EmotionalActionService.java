package ru.daniilazarnov.bot.core.emotional;

import ru.daniilazarnov.common.model.Actor;
import ru.daniilazarnov.common.model.Operation;

public interface EmotionalActionService {

    Operation chooseOperation(String sessionId, Actor actor);

}
