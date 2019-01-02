package ru.daniilazarnov.calc.storage.converter;

import org.springframework.stereotype.Component;
import ru.daniilazarnov.common.model.Event;
import ru.daniilazarnov.common.model.UnityLogRow;

@Component
public class UnityEventConverter implements EventConverter<UnityLogRow> {

    private final TimeConverter timeConverter;
    private final ActionFactory actionFactory;
    private final ActorFactory actorFactory;

    public UnityEventConverter(ru.daniilazarnov.calc.storage.converter.TimeConverter timeConverter, ru.daniilazarnov.calc.storage.converter.ActionFactory actionFactory, ru.daniilazarnov.calc.storage.converter.ActorFactory actorFactory) {
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
                .setMultiValues("X", logRow.getX())
                .setMultiValues("Y", logRow.getY())
                .setMultiValues("Z", logRow.getZ())
                .build();
    }

}
