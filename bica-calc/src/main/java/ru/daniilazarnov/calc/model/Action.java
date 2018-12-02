package ru.daniilazarnov.calc.model;

public class Action {

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

}
