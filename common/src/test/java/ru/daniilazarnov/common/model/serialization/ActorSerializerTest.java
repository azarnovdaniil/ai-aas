package ru.daniilazarnov.common.model.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.Test;
import ru.daniilazarnov.common.model.entity.Actor;
import ru.daniilazarnov.common.model.serialization.json.serializers.ActorSerializer;

import static org.junit.jupiter.api.Assertions.assertEquals;

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