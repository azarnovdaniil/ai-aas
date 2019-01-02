package ru.daniilazarnov.calc.storage.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Service;
import ru.daniilazarnov.calc.storage.serialization.csv.CsvEventSerializer;
import ru.daniilazarnov.common.model.*;
import ru.daniilazarnov.common.serialization.json.serializers.*;

@Service
public class CalcSerializer implements Serializer {

    private final CsvEventSerializer csvEventSerializer;
    private final ObjectMapper mapper = new ObjectMapper();

    public CalcSerializer(CsvEventSerializer csvEventSerializer) {
        this.csvEventSerializer = csvEventSerializer;

        SimpleModule module = new SimpleModule();
        module.addSerializer(Game.class, new GameSerializer());
        module.addSerializer(Event.class, new EventSerializer());
        module.addSerializer(Action.class, new ActionSerializer());
        module.addSerializer(Actor.class, new ActorSerializer());
        module.addSerializer(Appraisal.class, new AppraisalSerializer());
        module.addSerializer(State.class, new StateSerializer());

        this.mapper.registerModule(module);
    }

    @Override
    public String toCSV(Event event) {
        return csvEventSerializer.serialize(event);
    }

    @Override
    public String toJSON(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
