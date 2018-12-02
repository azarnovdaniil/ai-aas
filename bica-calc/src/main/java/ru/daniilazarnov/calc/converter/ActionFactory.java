package ru.daniilazarnov.calc.converter;

import org.springframework.stereotype.Component;
import ru.daniilazarnov.calc.model.Action;
import ru.daniilazarnov.calc.model.ActionType;
import ru.daniilazarnov.calc.model.Appraisal;
import ru.daniilazarnov.calc.property.CalcProperties;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class ActionFactory {

    private final Map<String, Action> actions = new HashMap<>();
    private final Set<String> systemActions = new HashSet<>();

    private static final Appraisal ZERO_APPRAISAL = Appraisal.valueOf(0.0, 0.0);

    private Action convert(CalcProperties.Action action) {
        return Action.builder()
                .setActionName(action.getName())
                .setAppraisal(Appraisal.valueOf(action.getValence(), action.getDominance()))
                .setActionType(ActionType.of(action.getType()))
                .build();
    }

    public ActionFactory(CalcProperties properties) {
        properties.getActions().forEach(action -> actions.put(action.getName().trim().toUpperCase(), convert(action)));
        properties.getSystemActions().forEach(s -> systemActions.add(s.trim().toUpperCase()));
    }

    Action getAction(String actionName) {
        String aName = actionName.trim().toUpperCase();

        if (systemActions.stream().anyMatch(aName::startsWith)) {
            return Action.builder()
                    .setActionName(actionName)
                    .setAppraisal(ZERO_APPRAISAL)
                    .setActionType(ActionType.SYSTEM)
                    .build();
        }

        return actions.get(aName);
    }
}
