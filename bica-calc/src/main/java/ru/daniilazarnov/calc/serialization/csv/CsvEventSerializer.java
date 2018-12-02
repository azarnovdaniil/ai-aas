package ru.daniilazarnov.calc.serialization.csv;

import org.springframework.stereotype.Component;
import ru.daniilazarnov.calc.property.CalcProperties;
import ru.daniilazarnov.common.model.Event;
import ru.daniilazarnov.common.model.State;

import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

@Component
public class CsvEventSerializer {

    private final DateTimeFormatter formatter;
    private final String delimiter;

    public CsvEventSerializer(CalcProperties properties) {
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

        for (Number value : event.getMultiValueMap().values()) {
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
