package ru.daniilazarnov.calc.storage.serialization;

import ru.daniilazarnov.common.model.data.Event;

public interface Serializer {

    String toCSV(Event event);

    String toJSON(Object object);

}
