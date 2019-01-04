package ru.daniilazarnov.common.model.serialization.json.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.daniilazarnov.common.model.data.Actor;

import java.io.IOException;

public class ActorSerializer extends JsonSerializer<Actor> {

    @Override
    public void serialize(Actor actor, JsonGenerator jGen, SerializerProvider sPr) throws IOException {
        jGen.writeStartObject();

        jGen.writeStringField("name", actor.getName());
        jGen.writeStringField("type", actor.getType());

        jGen.writeEndObject();
    }
}
