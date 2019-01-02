package ru.daniilazarnov.common.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.daniilazarnov.common.serialization.json.deserializers.StateDeserializer;
import ru.daniilazarnov.common.serialization.json.serializers.StateSerializer;

import java.util.Objects;

@JsonSerialize(using = StateSerializer.class)
@JsonDeserialize(using = StateDeserializer.class)
public class State {

    private final Actor actor;
    private final Actor target;
    private Appraisal appraisal;

    private State(Actor actor, Actor target, Appraisal appraisal) {
        this.actor = actor;
        this.target = target;
        this.appraisal = appraisal;
    }

    public static State valueOf(Actor actor, Actor target, Appraisal appraisal) {
        return new State(actor, target, appraisal);
    }

    public Actor getActor() {
        return actor;
    }

    public Actor getTarget() {
        return target;
    }

    public Appraisal getAppraisal() {
        return appraisal;
    }

    public void setAppraisal(Appraisal appraisal) {
        this.appraisal = appraisal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        return Objects.equals(actor, state.actor) && Objects.equals(target, state.target);
    }

    @Override
    public int hashCode() {
        int result = actor.hashCode();
        result = 31 * result + target.hashCode();
        return result;
    }
}
