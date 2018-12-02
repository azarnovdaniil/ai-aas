package ru.daniilazarnov.calc.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.Test;
import ru.daniilazarnov.calc.domain.Actor;
import ru.daniilazarnov.calc.serialization.json.ActorSerializer;

import static org.junit.jupiter.api.Assertions.*;

class ActorSerializerTest {

    @Test
    void name() throws JsonProcessingException {

        Actor myItem = Actor.valueOf("A");
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(Actor.class, new ActorSerializer());
        mapper.registerModule(module);

        String serialized = mapper.writeValueAsString(myItem);

        assertEquals("{\"name\":\"A\"}", serialized, "");

    }
}