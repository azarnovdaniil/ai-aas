package ru.daniilazarnov.web.dto;

import javax.vecmath.Point3d;

public class EventTO {

    private String actionName;
    private String actorName;
    private String targetName;
    private Point3d point3d;

    public EventTO() {
        // need for Jackson
    }

    public EventTO(String actionName, String actorName, String targetName, Point3d point3d) {
        this.actionName = actionName;
        this.actorName = actorName;
        this.targetName = targetName;
        this.point3d = point3d;
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

    public Point3d getPoint3d() {
        return point3d;
    }

    public void setPoint3d(Point3d point3d) {
        this.point3d = point3d;
    }

}
