package ru.daniilazarnov.calc.converter;

import ru.daniilazarnov.calc.domain.Action;
import ru.daniilazarnov.calc.domain.Appraisal;
import ru.daniilazarnov.calc.domain.Event;
import ru.daniilazarnov.calc.domain.UnityLogRow;
import ru.daniilazarnov.calc.property.CalcProperties;

import java.util.function.Function;

public final class ActionConverter {

    private ActionConverter() {
    }

    public static final Function<CalcProperties.Action, Action> action_action = action -> new Action(action.getName(), Appraisal.valueOf(action.getValence(), action.getDominance()));


}
