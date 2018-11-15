package ru.daniilazarnov.bot.core.domain;

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

    @Override
    public String toString() {
        return "Operation{" +
                "action=" + action +
                ", actor=" + actor +
                ", target=" + target +
                '}';
    }

}
