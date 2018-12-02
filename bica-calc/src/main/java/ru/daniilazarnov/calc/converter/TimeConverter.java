package ru.daniilazarnov.calc.converter;

import org.springframework.stereotype.Component;
import ru.daniilazarnov.calc.property.CalcProperties;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TimeConverter {

    private final DateTimeFormatter dateTimeFormatter;

    public TimeConverter(CalcProperties properties) {
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(properties.getDateTimeFormatter());
    }

    LocalDateTime stringToDate(String str) {
        return LocalDateTime.parse(str, dateTimeFormatter);
    }

}
