package ru.daniilazarnov.common.model.entity;

public abstract class LogRow {

    protected String timeStamp;
    protected String sessionId;
    protected String action = "";
    protected String actor = "";
    protected String target = "";

    public abstract void setValue(String[] values, int i);

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getAction() {
        return action;
    }

    public String getActor() {
        return actor;
    }

    public String getTarget() {
        return target;
    }

}
