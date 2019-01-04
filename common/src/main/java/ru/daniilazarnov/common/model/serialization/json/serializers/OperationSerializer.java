package ru.daniilazarnov.common.model.serialization.json.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.daniilazarnov.common.model.data.Operation;

import java.io.IOException;

public class OperationSerializer extends JsonSerializer<Operation> {

    @Override
    public void serialize(Operation event, JsonGenerator jGen, SerializerProvider sPr) throws IOException {
        jGen.writeStartObject();

        jGen.writeObjectField("action", event.getAction());
        jGen.writeObjectField("target", event.getTarget());
        jGen.writeObjectField("multiValues", event.getMultiValues());

        jGen.writeEndObject();
    }
}
