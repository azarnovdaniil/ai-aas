package ru.daniilazarnov.common.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.daniilazarnov.common.serialization.json.deserializers.EventDeserializer;
import ru.daniilazarnov.common.serialization.json.serializers.EventSerializer;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@JsonSerialize(using = EventSerializer.class)
@JsonDeserialize(using = EventDeserializer.class)
public class Event {

    private LocalDateTime localDateTime;
    private String sessionId;
    private Action action;
    private Actor target;
    private Actor actor;
    private Map<String, Number> multiValues = new LinkedHashMap<>();
    private Set<State> appraisalStateSet = new LinkedHashSet<>();

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    private Event() {
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

    public Map<String, Number> getMultiValues() {
        return multiValues;
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

        public Builder setMultiValues(String key, Number value) {
            Event.this.multiValues.put(key, value);
            return this;
        }

        public Builder setAppraisalStateSet(Set<State> states) {
            Event.this.appraisalStateSet.addAll(states);
            return this;
        }

        public Event build() {
            return Event.this;
        }

    }

}
