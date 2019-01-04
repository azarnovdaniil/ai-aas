package ru.daniilazarnov.common.model.dto;

public class EventTO {

    private final long timestamp;
    private final String sessionId;
    private final String actor;
    private final OperationTO operation;

    public EventTO(long timestamp, String sessionId, String actor, OperationTO operation) {
        this.timestamp = timestamp;
        this.sessionId = sessionId;
        this.actor = actor;
        this.operation = operation;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getActor() {
        return actor;
    }

    public OperationTO getOperation() {
        return operation;
    }
}
