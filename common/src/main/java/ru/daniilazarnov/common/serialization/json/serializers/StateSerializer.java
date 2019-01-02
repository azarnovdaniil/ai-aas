package ru.daniilazarnov.common.serialization.json.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.daniilazarnov.common.model.State;

import java.io.IOException;

public class StateSerializer extends JsonSerializer<State> {

    @Override
    public void serialize(State state, JsonGenerator jGen, SerializerProvider sPr) throws IOException {
        jGen.writeStartObject();

        jGen.writeObjectField("actor", state.getActor());
        jGen.writeObjectField("target", state.getTarget());
        jGen.writeObjectField("appraisal", state.getAppraisal());

        jGen.writeEndObject();
    }
}
