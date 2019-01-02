package ru.daniilazarnov.common.model.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.Test;
import ru.daniilazarnov.common.model.data.Action;
import ru.daniilazarnov.common.model.data.ActionType;
import ru.daniilazarnov.common.model.data.Appraisal;
import ru.daniilazarnov.common.model.serialization.json.serializers.ActionSerializer;
import ru.daniilazarnov.common.model.serialization.json.serializers.AppraisalSerializer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActionSerializerTest {

    @Test
    void name() throws JsonProcessingException {

        Action myItem = Action.builder()
                .setActionName("Kick")
                .setActionType(ActionType.NORMAL)
                .setAppraisal(Appraisal.valueOf(0.0, 0.0))
                .build();

        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(Action.class, new ActionSerializer());
        module.addSerializer(Appraisal.class, new AppraisalSerializer());
        mapper.registerModule(module);

        String serialized = mapper.writeValueAsString(myItem);

        assertEquals("{\"name\":\"Kick\",\"type\":\"NORMAL\",\"appraisal\":{\"valence\":0.0,\"dominance\":0.0}}", serialized, "");
    }
}
