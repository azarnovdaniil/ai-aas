package ru.daniilazarnov.calc.domain;

public class Action {

    private final String actionName;
    private final Appraisal appraisal;

    public Action(String actionName, Appraisal appraisal) {
        this.actionName = actionName;
        this.appraisal = appraisal;
    }

    public String getActionName() {
        return actionName;
    }

    public Appraisal getAppraisal() {
        return appraisal;
    }

    @Override
    public String toString() {
        return "Action{" +
                "actionName='" + actionName + '\'' +
                ", appraisal=" + appraisal +
                '}';
    }
}
