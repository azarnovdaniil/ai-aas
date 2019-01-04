package ru.daniilazarnov.bot.transport.converter;

import org.springframework.stereotype.Component;
import ru.daniilazarnov.common.model.dto.EventTO;
import ru.daniilazarnov.common.model.entity.Actor;
import ru.daniilazarnov.common.model.entity.Event;

import java.time.Instant;

@Component
public class EventConverter {

    private final OperationConverter operationConverter;

    public EventConverter(OperationConverter operationConverter) {
        this.operationConverter = operationConverter;
    }

    public Event convert(EventTO eventTO) {
        return Event.newBuilder()
                .setZonedDateTime(Instant.ofEpochMilli(eventTO.getTimestamp()))
                .setSessionId(eventTO.getSessionId())
                .setActor(Actor.valueOf(eventTO.getActor()))
                .setOperation(operationConverter.convert(eventTO.getOperation()))
                .build();
    }

}
