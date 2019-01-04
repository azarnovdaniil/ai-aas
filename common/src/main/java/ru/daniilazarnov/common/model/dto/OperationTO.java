package ru.daniilazarnov.common.model.dto;

import java.util.Map;

public class OperationTO {

    private final String action;
    private final String target;
    private final String gameObject;
    private final Map<String, Number> multiValues;

    public OperationTO(String action, String target, String gameObject, Map<String, Number> multiValues) {
        this.action = action;
        this.target = target;
        this.gameObject = gameObject;
        this.multiValues = multiValues;
    }

    public String getAction() {
        return action;
    }

    public String getTarget() {
        return target;
    }

    public String getGameObject() {
        return gameObject;
    }

    public Map<String, Number> getMultiValues() {
        return multiValues;
    }
}
