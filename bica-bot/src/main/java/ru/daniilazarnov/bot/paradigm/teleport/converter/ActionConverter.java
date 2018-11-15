package ru.daniilazarnov.bot.paradigm.teleport.converter;

import ru.daniilazarnov.bot.paradigm.teleport.config.TeleportConfig;
import ru.daniilazarnov.bot.core.domain.Action;
import ru.daniilazarnov.bot.core.domain.Appraisal;

import java.util.function.Function;

public class ActionConverter {

    private ActionConverter() {
    }

    public static final Function<TeleportConfig.Action, Action> action_action = action -> new Action(action.getName(), Appraisal.valueOf(action.getValence(), action.getDominance()));

}
