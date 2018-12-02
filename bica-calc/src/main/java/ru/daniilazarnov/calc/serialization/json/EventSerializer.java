package ru.daniilazarnov.calc.serialization.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.daniilazarnov.calc.model.Event;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class EventSerializer extends JsonSerializer<Event> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy HH:mm:ss:[SSS][SS][S]");

    @Override
    public void serialize(Event event, JsonGenerator jGen, SerializerProvider sPr) throws IOException {
        jGen.writeStartObject();

        jGen.writeStringField("timestamp", event.getLocalDateTime().format(formatter));
        jGen.writeStringField("sessionId", event.getSessionId());
        jGen.writeObjectField("action", event.getAction());
        jGen.writeObjectField("actor", event.getActor());
        jGen.writeObjectField("target", event.getTarget());
        jGen.writeObjectField("multi-value", event.getMultiValueMap());
        jGen.writeObjectField("states", event.getAppraisalStateSet());

        jGen.writeEndObject();
    }
}
