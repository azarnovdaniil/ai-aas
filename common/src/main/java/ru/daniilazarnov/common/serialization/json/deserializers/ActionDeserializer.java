package ru.daniilazarnov.common.serialization.json.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ru.daniilazarnov.common.model.Action;
import ru.daniilazarnov.common.model.ActionType;
import ru.daniilazarnov.common.model.Appraisal;

import java.io.IOException;

public class ActionDeserializer extends JsonDeserializer<Action> {

    @Override
    public Action deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        String name = node.get("name").asText();
        String type = node.get("type").asText();
        Appraisal appraisal = node.get("appraisal").traverse(jp.getCodec()).readValueAs(Appraisal.class);

        return Action.builder()
                .setAppraisal(appraisal)
                .setActionName(name)
                .setActionType(ActionType.valueOf(type))
                .build();
    }
}
