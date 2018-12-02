package ru.daniilazarnov.bot.paradigm.teleport.converter;

import ru.daniilazarnov.common.model.Action;
import ru.daniilazarnov.common.model.ActionType;
import ru.daniilazarnov.common.model.Appraisal;

import java.util.function.Function;

public class ActionConverter {

    private ActionConverter() {
    }

    public static final Function<ru.daniilazarnov.common.property.Action, Action> action_action = action -> Action.builder()
            .setActionName(action.getName())
            .setAppraisal(Appraisal.valueOf(action.getValence(), action.getDominance()))
            .setActionType(ActionType.NORMAL)
            .build();

}
