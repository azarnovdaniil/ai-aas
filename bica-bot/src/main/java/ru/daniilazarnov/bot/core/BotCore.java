package ru.daniilazarnov.bot.core;

import ru.daniilazarnov.bot.core.domain.Action;
import ru.daniilazarnov.bot.core.domain.Actor;
import ru.daniilazarnov.bot.core.domain.Operation;

import java.util.Collection;
import java.util.List;

public interface BotCore {

    Operation actionHandle(List<Operation> actions);

    void initMemory(Collection<Actor> actors);

    void updateMemory(Action action, Actor actor);

}
