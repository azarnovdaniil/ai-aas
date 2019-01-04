package ru.daniilazarnov.common.model.serialization.json.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ru.daniilazarnov.common.model.entity.Actor;

import java.io.IOException;

public class ActorDeserializer extends JsonDeserializer<Actor> {

    @Override
    public Actor deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        String name = node.get("name").asText();

        return Actor.valueOf(name);
    }
}
