package ru.daniilazarnov.calc.storage.converter;

import org.springframework.stereotype.Component;
import ru.daniilazarnov.common.model.data.*;

import java.time.Instant;
import java.time.ZoneId;

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
        Action action = actionFactory.getAction(logRow.getAction());
        Actor target = actorFactory.getActor(logRow.getSessionId(), logRow.getTarget());

        Operation operation = Operation.of(action, target);

        Instant instant = timeConverter.stringToDate(logRow.getTimeStamp()).atZone(ZoneId.systemDefault()).toInstant();

        return Event.newBuilder()
                .setZonedDateTime(instant)
                .setSessionId(logRow.getSessionId())
                .setActor(actorFactory.getActor(logRow.getSessionId(), logRow.getActor()))
                .setOperation(operation)
                .setMultiValues("X", logRow.getX())
                .setMultiValues("Y", logRow.getY())
                .setMultiValues("Z", logRow.getZ())
                .build();
    }

}
