package ru.daniilazarnov.common.model.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.Test;
import ru.daniilazarnov.common.model.data.*;
import ru.daniilazarnov.common.model.serialization.json.serializers.ActionSerializer;
import ru.daniilazarnov.common.model.serialization.json.serializers.ActorSerializer;
import ru.daniilazarnov.common.model.serialization.json.serializers.AppraisalSerializer;
import ru.daniilazarnov.common.model.serialization.json.serializers.EventSerializer;

import java.time.Instant;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventSerializerTest {

    @Test
    void name() throws JsonProcessingException {

        Appraisal appraisal = Appraisal.valueOf(0.0, 0.0);

        Action action = Action.builder()
                .setActionName("Kick")
                .setActionType(ActionType.NORMAL)
                .setAppraisal(appraisal)
                .build();

        Actor actor = Actor.valueOf("A");
        Actor target = Actor.valueOf("B");

        Event event = Event.newBuilder()
                .setSessionId("123")
                .setZonedDateTime(Instant.EPOCH)
                .setActor(actor)
                .setOperation(Operation.of(action, target))
                .setMultiValues("X", 0.0)
                .build();

        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(Event.class, new EventSerializer());
        module.addSerializer(Action.class, new ActionSerializer());
        module.addSerializer(Actor.class, new ActorSerializer());
        module.addSerializer(Appraisal.class, new AppraisalSerializer());

        mapper.registerModule(module);

        String serialized = mapper.writeValueAsString(event);

        assertEquals("{\"timestamp\":\"10/10/2000 10:10:00:000000\",\"sessionId\":\"123\",\"action\":{\"name\":\"Kick\",\"type\":\"NORMAL\",\"appraisal\":{\"valence\":0.0,\"dominance\":0.0}},\"actor\":{\"name\":\"A\"},\"target\":{\"name\":\"B\"},\"multi-value\":{\"X\":0.0},\"states\":[]}", serialized, "");
    }
}
