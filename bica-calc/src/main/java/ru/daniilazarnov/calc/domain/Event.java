package ru.daniilazarnov.calc.domain;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Event {

    private LocalDateTime localDateTime;
    private String sessionId;
    private Action action;
    private Actor target;
    private Actor actor;
    private Map<String, Number> multiValueMap = new LinkedHashMap<>();
    private Set<State> appraisalStateSet = new LinkedHashSet<>();

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Action getAction() {
        return action;
    }

    public Actor getTarget() {
        return target;
    }

    public Actor getActor() {
        return actor;
    }

    public Map<String, Number> getMultiValueMap() {
        return multiValueMap;
    }

    public static Builder newBuilder() {
        return new Event().new Builder();
    }

    public Set<State> getAppraisalStateSet() {
        return appraisalStateSet;
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Builder setLocalDateTime(LocalDateTime localDateTime) {
            Event.this.localDateTime = localDateTime;
            return this;
        }

        public Builder setSessionId(String sessionId) {
            Event.this.sessionId = sessionId;
            return this;
        }

        public Builder setAction(Action action) {
            Event.this.action = action;
            return this;
        }

        public Builder setActor(Actor actor) {
            Event.this.actor = actor;
            return this;
        }

        public Builder setTarget(Actor target) {
            Event.this.target = target;
            return this;
        }

        public Builder setMultiValue(String key, Number value) {
            Event.this.multiValueMap.put(key, value);
            return this;
        }

        public Event build() {
            return Event.this;
        }

    }

}
