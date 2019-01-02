package ru.daniilazarnov.common.model.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.Test;
import ru.daniilazarnov.common.model.data.*;
import ru.daniilazarnov.common.model.serialization.json.serializers.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameSerializerTest {

    @Test
    void name() throws IOException {
        Appraisal appraisal = Appraisal.zero();

        Action action = Action.builder()
                .setActionName("Kick")
                .setActionType(ActionType.NORMAL)
                .setAppraisal(appraisal)
                .build();

        Actor actor = Actor.valueOf("A");
        Actor target = Actor.valueOf("B");

        Event event1 = Event.newBuilder()
                .setSessionId("123")
                .setLocalDateTime(LocalDateTime.of(2000, 10, 10, 10, 10))
                .setTarget(target)
                .setActor(actor)
                .setAction(action)
                .setMultiValues("X", 0.0)
                .build();

        Event event2 = Event.newBuilder()
                .setSessionId("123")
                .setLocalDateTime(LocalDateTime.of(2000, 10, 10, 10, 10))
                .setTarget(target)
                .setActor(actor)
                .setAction(action)
                .setMultiValues("X", 0.0)
                .build();

        Game game = new Game(List.of(event1, event2));

        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(Game.class, new GameSerializer());
        module.addSerializer(Event.class, new EventSerializer());
        module.addSerializer(Action.class, new ActionSerializer());
        module.addSerializer(Actor.class, new ActorSerializer());
        module.addSerializer(Appraisal.class, new AppraisalSerializer());

        mapper.registerModule(module);

        String serialized = mapper.writeValueAsString(game);

        assertEquals("{\"events\":[{\"timestamp\":\"10/10/2000 10:10:00:000000\",\"sessionId\":\"123\",\"action\":{\"name\":\"Kick\",\"type\":\"NORMAL\",\"appraisal\":{\"valence\":0.0,\"dominance\":0.0}},\"actor\":{\"name\":\"A\"},\"target\":{\"name\":\"B\"},\"multi-value\":{\"X\":0.0},\"states\":[]},{\"timestamp\":\"10/10/2000 10:10:00:000000\",\"sessionId\":\"123\",\"action\":{\"name\":\"Kick\",\"type\":\"NORMAL\",\"appraisal\":{\"valence\":0.0,\"dominance\":0.0}},\"actor\":{\"name\":\"A\"},\"target\":{\"name\":\"B\"},\"multi-value\":{\"X\":0.0},\"states\":[]}]}", serialized, "");
    }
}
