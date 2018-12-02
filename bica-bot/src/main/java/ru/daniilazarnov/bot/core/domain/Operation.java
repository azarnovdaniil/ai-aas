package ru.daniilazarnov.bot.core.domain;

import ru.daniilazarnov.common.model.Action;
import ru.daniilazarnov.common.model.Actor;

public class Operation {

    private final Action action;
    private final Actor actor;
    private final Actor target;

    public Operation(Action action, Actor actor, Actor target) {
        this.action = action;
        this.actor = actor;
        this.target = target;
    }

    public Action getAction() {
        return action;
    }

    public Actor getActor() {
        return actor;
    }

    public Actor getTarget() {
        return target;
    }

}
