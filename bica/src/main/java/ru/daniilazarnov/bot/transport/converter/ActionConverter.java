package ru.daniilazarnov.bot.transport.converter;

import org.springframework.stereotype.Component;
import ru.daniilazarnov.common.config.GameConfig;
import ru.daniilazarnov.common.model.entity.Action;
import ru.daniilazarnov.common.model.entity.ActionType;
import ru.daniilazarnov.common.model.entity.Appraisal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class ActionConverter {

    private final Map<String, Action> actions = new HashMap<>();
    private final Set<String> systemActions = new HashSet<>();

    public ActionConverter(GameConfig gameConfig) {
        gameConfig.getActions().forEach(action -> actions.put(action.getName().trim().toUpperCase(), action));
        gameConfig.getSystemActions().forEach(s -> systemActions.add(s.trim().toUpperCase()));
    }

    public Action convert(String actionName) {
        String aName = actionName.trim().toUpperCase();

        if (systemActions.stream().anyMatch(aName::startsWith)) {
            return Action.builder()
                    .setActionName(actionName)
                    .setAppraisal(Appraisal.zero())
                    .setActionType(ActionType.SYSTEM)
                    .build();
        }

        return actions.get(aName);
    }
}
