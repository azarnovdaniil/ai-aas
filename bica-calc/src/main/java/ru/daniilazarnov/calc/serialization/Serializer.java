package ru.daniilazarnov.calc.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.daniilazarnov.calc.model.Event;

public interface Serializer {

    String toCSV(Event event);

    String toJSON(Object object) throws JsonProcessingException;

}
