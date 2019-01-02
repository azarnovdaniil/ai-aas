package ru.daniilazarnov.common.serialization.json.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ru.daniilazarnov.common.model.Action;
import ru.daniilazarnov.common.model.Actor;
import ru.daniilazarnov.common.model.Event;
import ru.daniilazarnov.common.model.State;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class EventDeserializer extends JsonDeserializer<Event> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy HH:mm:ss:[SSS][SS][S]");

    @Override
    public Event deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        String timestamp = node.get("timestamp").asText();
        String sessionId = node.get("sessionId").asText();
        Actor actor = node.get("actor").traverse(jp.getCodec()).readValueAs(Actor.class);
        Actor target = node.get("target").traverse(jp.getCodec()).readValueAs(Actor.class);
        Action action = node.get("action").traverse(jp.getCodec()).readValueAs(Action.class);

        Set<State> states = new LinkedHashSet<>();

        Iterator<JsonNode> elements = node.findValues("states").get(0).elements();

        while (elements.hasNext()) {
            states.add(elements.next().traverse(jp.getCodec()).readValueAs(State.class));
        }

        return Event.newBuilder()
                .setLocalDateTime(LocalDateTime.parse(timestamp, formatter))
                .setSessionId(sessionId)
                .setAction(action)
                .setActor(actor)
                .setTarget(target)
                .setAppraisalStateSet(states)
                .build();
    }
}
