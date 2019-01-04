package ru.daniilazarnov.calc.storage.converter;

import ru.daniilazarnov.common.model.entity.Event;
import ru.daniilazarnov.common.model.entity.LogRow;

public interface EventConverter<L extends LogRow> {

    Event logToEvent(L logRow);

}
