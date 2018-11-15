package ru.daniilazarnov.bot.transport.domain;

import com.opencsv.bean.CsvBindByName;

public class Event {

    @CsvBindByName
    private String timeStamp;
    @CsvBindByName
    private String sessionId;
    @CsvBindByName
    private String participantId;
    @CsvBindByName
    private String action;
    @CsvBindByName
    private String actor;
    @CsvBindByName
    private double x;
    @CsvBindByName
    private double y;
    @CsvBindByName
    private double z;
    @CsvBindByName
    private String target;

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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

}
