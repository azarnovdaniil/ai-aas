package ru.daniilazarnov.common.model.serialization.json.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ru.daniilazarnov.common.model.entity.Action;
import ru.daniilazarnov.common.model.entity.Actor;
import ru.daniilazarnov.common.model.entity.Operation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OperationDeserializer extends JsonDeserializer<Operation> {

    private static final TypeReference<Map<String, Number>> multiValuesTypeRef = new TypeReference<>() {
    };

    @Override
    public Operation deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        Actor target = node.get("target").traverse(jp.getCodec()).readValueAs(Actor.class);

        JsonNode actionNode = node.get("action");
        Action action = actionNode == null ? Action.none() : actionNode.traverse(jp.getCodec()).readValueAs(Action.class);

        Map<String, Number> multiValues = new HashMap<>();
        JsonNode multiValuesNode = node.get("multiValues");
        if (multiValuesNode != null) {
            multiValues = multiValuesNode.traverse(jp.getCodec()).readValueAs(multiValuesTypeRef);
        }

        return Operation.of(action, target, multiValues);
    }
}
