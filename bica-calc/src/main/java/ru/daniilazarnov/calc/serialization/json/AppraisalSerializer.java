package ru.daniilazarnov.calc.serialization.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.daniilazarnov.calc.model.Appraisal;

import java.io.IOException;

public class AppraisalSerializer extends JsonSerializer<Appraisal> {

    @Override
    public void serialize(Appraisal appraisal, JsonGenerator jGen, SerializerProvider sPr) throws IOException {
        jGen.writeStartObject();

        jGen.writeNumberField("valence", appraisal.getValence());
        jGen.writeNumberField("dominance", appraisal.getDominance());

        jGen.writeEndObject();
    }
}
