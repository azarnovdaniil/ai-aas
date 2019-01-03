package ru.daniilazarnov.calc.storage.converter;

import org.springframework.stereotype.Component;
import ru.daniilazarnov.calc.property.ParserProperties;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TimeConverter {

    private final DateTimeFormatter formatter;

    public TimeConverter(ParserProperties properties) {
        this.formatter = DateTimeFormatter.ofPattern(properties.getFormatter());
    }

    LocalDateTime stringToDate(String str) {
        return LocalDateTime.parse(str, formatter);
    }

}
