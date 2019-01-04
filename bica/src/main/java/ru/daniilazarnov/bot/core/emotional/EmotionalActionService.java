package ru.daniilazarnov.bot.core.emotional;

import ru.daniilazarnov.common.model.entity.Actor;
import ru.daniilazarnov.common.model.entity.Operation;

public interface EmotionalActionService {

    Operation chooseOperation(String sessionId, Actor actor);

}
