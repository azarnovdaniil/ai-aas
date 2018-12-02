package ru.daniilazarnov.calc.converter;

import org.springframework.stereotype.Component;
import ru.daniilazarnov.calc.domain.Actor;
import ru.daniilazarnov.calc.domain.Event;
import ru.daniilazarnov.calc.domain.UnityLogRow;

@Component
public class LogConverter {

    private final TimeConverter timeConverter;
    private final ActionFactory factory;

    public LogConverter(TimeConverter timeConverter, ActionFactory factory) {
        this.timeConverter = timeConverter;
        this.factory = factory;
    }

    public Event logToEvent(UnityLogRow logRow) {
        return Event.newBuilder()
                .setLocalDateTime(timeConverter.stringToDate(logRow.getTimeStamp()))
                .setSessionId(logRow.getSessionId())
                .setAction(factory.getAction(logRow.getAction()))
                .setActor(Actor.valueOf(logRow.getActor()))
                .setTarget(Actor.valueOf(logRow.getTarget()))
                .setMultiValue("X", logRow.getX())
                .setMultiValue("Y", logRow.getY())
                .setMultiValue("Z", logRow.getZ())
                .build();
    }

}
