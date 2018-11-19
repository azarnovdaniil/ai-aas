package ru.daniilazarnov.bot.transport.converter;

import ru.daniilazarnov.bot.transport.domain.Event;
import ru.daniilazarnov.bot.transport.dto.EventTO;

import java.util.Map;
import java.util.function.Function;

public final class EventConverter {

    private EventConverter() {
    }

    public static final Function<Event, EventTO> event_eventTO = event -> {
        EventTO eventTO = new EventTO(event.getTimeStamp(), event.getSessionId(), event.getParticipantId(), event.getAction(), event.getActor(), event.getTarget());
        Map<String, String> otherAttributes = eventTO.getOtherAttributes();
        otherAttributes.put("x", String.valueOf(event.getX()));
        otherAttributes.put("y", String.valueOf(event.getY()));
        otherAttributes.put("z", String.valueOf(event.getZ()));

        return eventTO;
    };

}
