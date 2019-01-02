package ru.daniilazarnov.calc.storage.converter;

import ru.daniilazarnov.common.model.data.Event;
import ru.daniilazarnov.common.model.data.LogRow;

public interface EventConverter<L extends LogRow> {

    Event logToEvent(L logRow);

}
