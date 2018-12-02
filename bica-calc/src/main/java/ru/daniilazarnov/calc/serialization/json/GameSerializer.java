package ru.daniilazarnov.calc.serialization.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.daniilazarnov.calc.domain.Game;

import java.io.IOException;

public class GameSerializer extends JsonSerializer<Game> {

    @Override
    public void serialize(Game game, JsonGenerator jGen, SerializerProvider sPr) throws IOException {
        jGen.writeStartObject();

        jGen.writeObjectField("events", game.getEvents());

        jGen.writeEndObject();
    }
}
