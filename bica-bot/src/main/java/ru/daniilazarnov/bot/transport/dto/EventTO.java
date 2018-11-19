package ru.daniilazarnov.bot.transport.dto;

import java.util.HashMap;
import java.util.Map;

public class EventTO {

    private String timeStamp;
    private String sessionId;
    private String participantId;
    private String actionName;
    private String actorName;
    private String targetName;

    private Map<String, String> otherAttributes = new HashMap<>();

    public EventTO() {
        // need for Jackson
    }

    public EventTO(String timeStamp, String sessionId, String participantId, String actionName, String actorName, String targetName) {
        this.timeStamp = timeStamp;
        this.sessionId = sessionId;
        this.participantId = participantId;
        this.actionName = actionName;
        this.actorName = actorName;
        this.targetName = targetName;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public Map<String, String> getOtherAttributes() {
        return otherAttributes;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getActionName() {
        return actionName;
    }

    public String getActorName() {
        return actorName;
    }

    public String getTargetName() {
        return targetName;
    }

}
