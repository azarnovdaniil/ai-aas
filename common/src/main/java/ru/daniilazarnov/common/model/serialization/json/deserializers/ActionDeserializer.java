package ru.daniilazarnov.common.model.serialization.json.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ru.daniilazarnov.common.model.entity.Action;
import ru.daniilazarnov.common.model.entity.ActionType;
import ru.daniilazarnov.common.model.entity.Appraisal;

import java.io.IOException;

public class ActionDeserializer extends JsonDeserializer<Action> {

    @Override
    public Action deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        String name = node.get("name").asText();

        JsonNode typeNode = node.get("type");
        ActionType type = typeNode != null ? ActionType.valueOf(typeNode.asText()) : ActionType.NORMAL;

        JsonNode appraisalNode = node.get("appraisal");
        Appraisal appraisal = appraisalNode != null ? appraisalNode.traverse(jp.getCodec()).readValueAs(Appraisal.class) : Appraisal.zero();

        return Action.builder()
                .setAppraisal(appraisal)
                .setActionName(name)
                .setActionType(type)
                .build();
    }
}
