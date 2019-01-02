package ru.daniilazarnov.calc.storage.serialization.csv;

import org.springframework.stereotype.Component;
import ru.daniilazarnov.calc.property.StorageProperties;
import ru.daniilazarnov.common.model.data.Event;
import ru.daniilazarnov.common.model.data.State;

import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

@Component
public class CsvEventSerializer {

    private final DateTimeFormatter formatter;
    private final String delimiter;

    public CsvEventSerializer(StorageProperties properties) {
        this.formatter = DateTimeFormatter.ofPattern(properties.getDateTimeFormatter());
        this.delimiter = properties.getCsvDelimiter();
    }

    public String serialize(Event event) {
        StringJoiner joiner = new StringJoiner(delimiter);
        joiner.add(event.getLocalDateTime().format(formatter))
                .add(event.getSessionId())
                .add(event.getActor().getName())
                .add(event.getAction().getActionName())
                .add(event.getTarget().getName());

        for (Number value : event.getMultiValues().values()) {
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
