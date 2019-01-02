package ru.daniilazarnov.calc.storage.converter;

import org.springframework.stereotype.Component;
import ru.daniilazarnov.common.config.GameConfig;
import ru.daniilazarnov.common.model.Action;
import ru.daniilazarnov.common.model.ActionType;
import ru.daniilazarnov.common.model.Appraisal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class ActionFactory {

    private final Map<String, Action> actions = new HashMap<>();
    private final Set<String> systemActions = new HashSet<>();

    private static final Appraisal ZERO_APPRAISAL = Appraisal.valueOf(0.0, 0.0);

    public ActionFactory(GameConfig gameConfig) {
        gameConfig.getActions().forEach(action -> actions.put(action.getActionName().trim().toUpperCase(), action));
        gameConfig.getSystemActions().forEach(s -> systemActions.add(s.trim().toUpperCase()));
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
