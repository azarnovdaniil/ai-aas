package ru.daniilazarnov.common.serialization.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.Test;
import ru.daniilazarnov.common.model.Action;
import ru.daniilazarnov.common.serialization.json.deserializers.ActionDeserializer;

import java.io.IOException;

class ActionDeserializerTest {

    @Test
    void name() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Action.class, new ActionDeserializer());
        mapper.registerModule(module);

        Action readValue = mapper.readValue("{\n" +
                "        \"name\": \"Move\",\n" +
                "        \"type\": \"SELF\",\n" +
                "        \"appraisal\": {\n" +
                "          \"valence\": 0.01,\n" +
                "          \"dominance\": 0.01\n" +
                "        }\n" +
                "      }", Action.class);

        System.out.println(readValue);
    }
}
