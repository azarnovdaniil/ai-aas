package ru.daniilazarnov.common.model.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.daniilazarnov.common.model.serialization.json.deserializers.ActionDeserializer;
import ru.daniilazarnov.common.model.serialization.json.serializers.ActionSerializer;

@JsonSerialize(using = ActionSerializer.class)
@JsonDeserialize(using = ActionDeserializer.class)
public class Action {

    private static final Action NONE = new Action("none", Appraisal.zero(), ActionType.NONE);

    private final String actionName;
    private final Appraisal appraisal;
    private final ActionType actionType;

    private Action(String actionName, Appraisal appraisal, ActionType actionType) {
        this.actionName = actionName;
        this.appraisal = appraisal;
        this.actionType = actionType;
    }

    public String getActionName() {
        return actionName;
    }

    public Appraisal getAppraisal() {
        return appraisal;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public static Action none() {
        return NONE;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String actionName;
        private Appraisal appraisal;
        private ActionType actionType;

        Builder() {
            // private constructor
        }

        public Builder setActionName(String actionName) {
            this.actionName = actionName;
            return this;
        }

        public Builder setAppraisal(Appraisal appraisal) {
            this.appraisal = appraisal;
            return this;
        }

        public Builder setActionType(ActionType actionType) {
            this.actionType = actionType;
            return this;
        }

        public Action build() {
            return new Action(actionName, appraisal, actionType);
        }

    }

    @Override
    public String toString() {
        return "Action{" +
                "actionName='" + actionName + '\'' +
                ", appraisal=" + appraisal +
                ", actionType=" + actionType +
                '}';
    }
}
