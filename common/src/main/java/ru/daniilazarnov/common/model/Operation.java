package ru.daniilazarnov.common.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.daniilazarnov.common.serialization.json.deserializers.OperationDeserializer;
import ru.daniilazarnov.common.serialization.json.serializers.OperationSerializer;

import java.util.Objects;

@JsonSerialize(using = OperationSerializer.class)
@JsonDeserialize(using = OperationDeserializer.class)
public class Operation {

    private final Action action;
    private final Actor target;

    private Operation(Action action, Actor target) {
        this.action = action;
        this.target = target;
    }

    public Action getAction() {
        return action;
    }

    public Actor getTarget() {
        return target;
    }

    public static Operation of(Action action, Actor target) {
        return new Operation(action, target);
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
}
