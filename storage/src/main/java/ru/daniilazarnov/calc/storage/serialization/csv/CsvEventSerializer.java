package ru.daniilazarnov.calc.storage.serialization.csv;

import org.springframework.stereotype.Component;
import ru.daniilazarnov.calc.property.ParserProperties;
import ru.daniilazarnov.common.model.data.Event;
import ru.daniilazarnov.common.model.data.State;

import java.util.StringJoiner;

@Component
public class CsvEventSerializer {

    private final String delimiter;

    public CsvEventSerializer(ParserProperties properties) {
        this.delimiter = properties.getDelimiter();
    }

    public String serialize(Event event) {
        StringJoiner joiner = new StringJoiner(delimiter);
        joiner.add(String.valueOf(event.getZonedDateTime().toInstant().toEpochMilli()))
                .add(event.getSessionId())
                .add(event.getActor().getName())
                .add(event.getOperation().getAction().getName())
                .add(event.getOperation().getTarget().getName());

        for (Number value : event.getOperation().getMultiValues().values()) {
            joiner.add(String.valueOf(value));
        }

        for (State state : event.getAppraisalStateSet()) {
            joiner.add(state.getActor().getName())
                    .add(state.getTarget().getName())
                    .add(String.valueOf(state.getAppraisal().getValence()))
                    .add(String.valueOf(state.getAppraisal().getDominance()));
        }

        return joiner.toString();
    }
}
