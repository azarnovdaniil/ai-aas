package ru.daniilazarnov.common.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.daniilazarnov.common.model.serialization.json.deserializers.OperationDeserializer;
import ru.daniilazarnov.common.model.serialization.json.serializers.OperationSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@JsonSerialize(using = OperationSerializer.class)
@JsonDeserialize(using = OperationDeserializer.class)
public class Operation {

    private static final Operation NONE = new Operation(Action.none(), Actor.none(), new HashMap<>());

    private final Action action;
    private final GameObject target;
    private final Map<String, Number> multiValues;

    private Operation(Action action, GameObject target, Map<String, Number> multiValues) {
        this.action = action;
        this.target = target;
        this.multiValues = multiValues;
    }

    public Action getAction() {
        return action;
    }

    public GameObject getTarget() {
        return target;
    }

    public Map<String, Number> getMultiValues() {
        return multiValues;
    }

    public static Operation of(Action action, GameObject target) {
        return of(action, target, new HashMap<>());
    }

    public static Operation of(Action action, GameObject target, Map<String, Number> multiValues) {
        return new Operation(action, target, multiValues);
    }

    public static Operation none() {
        return NONE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(action, operation.action) &&
                Objects.equals(target, operation.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(action, target);
    }

    @Override
    public String toString() {
        return "Operation{" +
                "action=" + action +
                ", target=" + target +
                '}';
    }
}
