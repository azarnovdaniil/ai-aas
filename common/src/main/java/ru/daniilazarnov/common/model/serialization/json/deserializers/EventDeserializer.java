package ru.daniilazarnov.common.model.serialization.json.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ru.daniilazarnov.common.model.data.Actor;
import ru.daniilazarnov.common.model.data.Event;
import ru.daniilazarnov.common.model.data.Operation;
import ru.daniilazarnov.common.model.data.State;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class EventDeserializer extends JsonDeserializer<Event> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy HH:mm:ss:[SSS][SS][S]");

    private static final TypeReference<LinkedHashSet<State>> statesTypeRef = new TypeReference<>() {
    };

    private static final TypeReference<Map<String, Number>> multiValuesTypeRef = new TypeReference<>() {
    };

    @Override
    public Event deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        String timestamp = node.get("timestamp").asText();
        String sessionId = node.get("sessionId").asText();
        Actor actor = node.get("actor").traverse(jp.getCodec()).readValueAs(Actor.class);
        Operation operation = node.get("operation").traverse(jp.getCodec()).readValueAs(Operation.class);

        Map<String, Number> multiValues = new HashMap<>();
        JsonNode multiValuesNode = node.get("multiValues");
        if (multiValuesNode != null) {
            multiValues = multiValuesNode.traverse(jp.getCodec()).readValueAs(multiValuesTypeRef);
        }

        Set<State> states = new LinkedHashSet<>();
        JsonNode statesNode = node.get("states");
        if (statesNode != null) {
            states = statesNode.traverse(jp.getCodec()).readValueAs(statesTypeRef);
        }

        return Event.newBuilder()
                .setLocalDateTime(LocalDateTime.parse(timestamp, formatter))
                .setSessionId(sessionId)
                .setActor(actor)
                .setOperation(operation)
                .setMultiValues(multiValues)
                .setAppraisalStateSet(states)
                .build();
    }
}
