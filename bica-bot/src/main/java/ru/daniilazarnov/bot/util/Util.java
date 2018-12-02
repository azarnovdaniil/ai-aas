package ru.daniilazarnov.bot.util;

import ru.daniilazarnov.common.model.Actor;

public final class Util {

    private Util() {
    }

    public static String actorKey(Actor actor) {
        return "BotTo" + actor.getName();
    }

    public static String targetKey(Actor actor) {
        return actor.getName() + "ToBot";
    }

}
