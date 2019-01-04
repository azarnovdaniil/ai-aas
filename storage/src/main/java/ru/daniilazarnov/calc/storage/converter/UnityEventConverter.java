package ru.daniilazarnov.calc.storage.converter;

import org.springframework.stereotype.Component;
import ru.daniilazarnov.common.model.data.*;

import java.time.Instant;
import java.time.ZoneId;
import java.util.LinkedHashMap;

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

        LinkedHashMap<String, Number> map = new LinkedHashMap<>();
        map.put("X", logRow.getX());
        map.put("Y", logRow.getY());
        map.put("Z", logRow.getZ());

        Operation operation = Operation.of(action, target, map);

        Instant instant = timeConverter.stringToDate(logRow.getTimeStamp()).atZone(ZoneId.systemDefault()).toInstant();

        return Event.newBuilder()
                .setZonedDateTime(instant)
                .setSessionId(logRow.getSessionId())
                .setActor(actorFactory.getActor(logRow.getSessionId(), logRow.getActor()))
                .setOperation(operation)
                .build();
    }

}
