package ru.daniilazarnov.common.model.serialization.json.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.daniilazarnov.common.model.data.Event;

import java.io.IOException;

public class EventSerializer extends JsonSerializer<Event> {

    @Override
    public void serialize(Event event, JsonGenerator jGen, SerializerProvider sPr) throws IOException {
        jGen.writeStartObject();

        jGen.writeNumberField("timestamp", event.getZonedDateTime().toInstant().toEpochMilli());
        jGen.writeStringField("sessionId", event.getSessionId());
        jGen.writeObjectField("actor", event.getActor());
        jGen.writeObjectField("operation", event.getOperation());
        jGen.writeObjectField("multiValues", event.getMultiValues());
        jGen.writeObjectField("states", event.getAppraisalStateSet());

        jGen.writeEndObject();
    }
}
