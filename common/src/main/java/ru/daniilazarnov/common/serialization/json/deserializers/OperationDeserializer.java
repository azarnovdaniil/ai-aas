package ru.daniilazarnov.common.serialization.json.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ru.daniilazarnov.common.model.Action;
import ru.daniilazarnov.common.model.Actor;
import ru.daniilazarnov.common.model.Operation;

import java.io.IOException;

public class OperationDeserializer extends JsonDeserializer<Operation> {

    @Override
    public Operation deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        Actor target = node.get("target").traverse(jp.getCodec()).readValueAs(Actor.class);
        Action action = node.get("action").traverse(jp.getCodec()).readValueAs(Action.class);

        return Operation.of(action, target);
    }
}
