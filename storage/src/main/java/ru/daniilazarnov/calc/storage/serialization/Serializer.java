package ru.daniilazarnov.calc.storage.serialization;

import ru.daniilazarnov.common.model.Event;

public interface Serializer {

    String toCSV(Event event);

    String toJSON(Object object);

}
