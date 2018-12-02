package ru.daniilazarnov.calc.converter;

import ru.daniilazarnov.common.model.Event;
import ru.daniilazarnov.common.model.LogRow;

public interface EventConverter<L extends LogRow> {

    Event logToEvent(L logRow);

}
