package ru.daniilazarnov.calc.storage.serialization;

import ru.daniilazarnov.common.model.entity.Event;

public interface Serializer {

    String toCSV(Event event);

}
