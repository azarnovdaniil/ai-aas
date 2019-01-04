package ru.daniilazarnov.common.model.serialization.json.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.daniilazarnov.common.model.entity.Action;

import java.io.IOException;

public class ActionSerializer extends JsonSerializer<Action> {

    @Override
    public void serialize(Action action, JsonGenerator jGen, SerializerProvider sPr) throws IOException {
        jGen.writeStartObject();

        jGen.writeStringField("name", action.getName());
        jGen.writeStringField("type", action.getType().name());
        jGen.writeObjectField("appraisal", action.getAppraisal());

        jGen.writeEndObject();
    }
}
