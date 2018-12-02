package ru.daniilazarnov.calc.converter;

import ru.daniilazarnov.calc.model.Event;
import ru.daniilazarnov.calc.model.LogRow;

public interface EventConverter<L extends LogRow> {

    Event logToEvent(L logRow);

}
