package ru.daniilazarnov.common.model.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.daniilazarnov.common.model.serialization.json.deserializers.EventDeserializer;
import ru.daniilazarnov.common.model.serialization.json.serializers.EventSerializer;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@JsonSerialize(using = EventSerializer.class)
@JsonDeserialize(using = EventDeserializer.class)
public class Event {

    private ZonedDateTime zonedDateTime;
    private String sessionId;
    private Actor actor;
    private Operation operation;
    private Map<String, Number> multiValues = new LinkedHashMap<>();
    private Set<State> appraisalStateSet = new LinkedHashSet<>();

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    private Event() {
    }

    public String getSessionId() {
        return sessionId;
    }

    public Operation getOperation() {
        return operation;
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

        public Builder setZonedDateTime(Instant instant) {
            Event.this.zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
            return this;
        }

        public Builder setSessionId(String sessionId) {
            Event.this.sessionId = sessionId;
            return this;
        }

        public Builder setOperation(Operation operation) {
            Event.this.operation = operation;
            return this;
        }

        public Builder setActor(Actor actor) {
            Event.this.actor = actor;
            return this;
        }

        public Builder setMultiValues(String key, Number value) {
            Event.this.multiValues.put(key, value);
            return this;
        }

        public Builder setMultiValues(Map<String, Number> multiValues) {
            Event.this.multiValues.putAll(multiValues);
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
