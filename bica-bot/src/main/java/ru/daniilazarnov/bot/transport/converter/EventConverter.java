package ru.daniilazarnov.bot.transport.converter;

import ru.daniilazarnov.bot.transport.domain.Event;
import ru.daniilazarnov.bot.transport.dto.EventTO;

import javax.vecmath.Point3d;
import java.util.function.Function;

public class EventConverter {

    private EventConverter() {
    }

    public static final Function<Event, EventTO> event_eventTO = event -> new EventTO(event.getAction(), event.getActor(), event.getTarget(), new Point3d(event.getX(), event.getY(), event.getZ()));

}
