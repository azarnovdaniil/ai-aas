package ru.daniilazarnov.common.model.serialization.json.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ru.daniilazarnov.common.model.entity.Appraisal;

import java.io.IOException;

public class AppraisalDeserializer extends JsonDeserializer<Appraisal> {

    @Override
    public Appraisal deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        double valence = (Double) node.get("valence").numberValue();
        double dominance = (Double) node.get("dominance").numberValue();

        return Appraisal.valueOf(valence, dominance);
    }
}
