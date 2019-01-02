package ru.daniilazarnov.common.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.daniilazarnov.common.serialization.json.deserializers.OperationDeserializer;
import ru.daniilazarnov.common.serialization.json.serializers.OperationSerializer;

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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Action action;
        private Actor target;

        private Builder() {
            // private constructor
        }

        public Builder setAction(Action action) {
            this.action = action;
            return this;
        }

        public Builder setTarget(Actor target) {
            this.target = target;
            return this;
        }

        public Operation build() {
            return new Operation(action, target);
        }
    }
}
