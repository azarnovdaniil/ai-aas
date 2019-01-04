package ru.daniilazarnov.common.model.serialization.json.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ru.daniilazarnov.common.model.entity.Actor;
import ru.daniilazarnov.common.model.entity.Appraisal;
import ru.daniilazarnov.common.model.entity.State;

import java.io.IOException;

public class StateDeserializer extends JsonDeserializer<State> {

    @Override
    public State deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        Actor actor = node.get("actor").traverse(jp.getCodec()).readValueAs(Actor.class);
        Actor target = node.get("target").traverse(jp.getCodec()).readValueAs(Actor.class);
        Appraisal appraisal = node.get("appraisal").traverse(jp.getCodec()).readValueAs(Appraisal.class);

        return State.valueOf(actor, target, appraisal);
    }
}
