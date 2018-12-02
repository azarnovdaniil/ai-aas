package ru.daniilazarnov.calc.converter;

import org.springframework.stereotype.Component;
import ru.daniilazarnov.calc.model.Event;
import ru.daniilazarnov.calc.model.UnityLogRow;

@Component
public class UnityEventConverter implements EventConverter<UnityLogRow> {

    private final TimeConverter timeConverter;
    private final ActionFactory actionFactory;
    private final ActorFactory actorFactory;

    public UnityEventConverter(TimeConverter timeConverter, ActionFactory actionFactory, ActorFactory actorFactory) {
        this.timeConverter = timeConverter;
        this.actionFactory = actionFactory;
        this.actorFactory = actorFactory;
    }

    @Override
    public Event logToEvent(UnityLogRow logRow) {
        return Event.newBuilder()
                .setLocalDateTime(timeConverter.stringToDate(logRow.getTimeStamp()))
                .setSessionId(logRow.getSessionId())
                .setAction(actionFactory.getAction(logRow.getAction()))
                .setActor(actorFactory.getActor(logRow.getSessionId(), logRow.getActor()))
                .setTarget(actorFactory.getActor(logRow.getSessionId(), logRow.getTarget()))
                .setMultiValue("X", logRow.getX())
                .setMultiValue("Y", logRow.getY())
                .setMultiValue("Z", logRow.getZ())
                .build();
    }

}
