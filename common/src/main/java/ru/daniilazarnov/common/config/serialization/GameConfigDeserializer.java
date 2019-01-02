package ru.daniilazarnov.common.config.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ru.daniilazarnov.common.config.GameConfig;
import ru.daniilazarnov.common.model.data.Action;
import ru.daniilazarnov.common.model.data.Appraisal;

import java.io.IOException;
import java.util.List;

public class GameConfigDeserializer extends JsonDeserializer<GameConfig> {

    private static final TypeReference<List<String>> systemActionsTypeReference = new TypeReference<>() {
    };

    private static final TypeReference<List<Action>> actionsTypeReference = new TypeReference<>() {
    };

    @Override
    public GameConfig deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        Appraisal initialAppraisal = node.get("initial-appraisal").traverse(jp.getCodec()).readValueAs(Appraisal.class);
        List<String> systemActions = node.get("system-actions").traverse(jp.getCodec()).readValueAs(systemActionsTypeReference);
        List<Action> actions = node.get("actions").traverse(jp.getCodec()).readValueAs(actionsTypeReference);

        return GameConfig.builder()
                .setInitialAppraisal(initialAppraisal)
                .setSystemActions(systemActions)
                .setActions(actions)
                .build();
    }
}
