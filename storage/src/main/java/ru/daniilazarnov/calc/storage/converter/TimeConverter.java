package ru.daniilazarnov.calc.storage.converter;

import org.springframework.stereotype.Component;
import ru.daniilazarnov.calc.property.StorageProperties;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TimeConverter {

    private final DateTimeFormatter dateTimeFormatter;

    public TimeConverter(StorageProperties properties) {
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(properties.getDateTimeFormatter());
    }

    LocalDateTime stringToDate(String str) {
        return LocalDateTime.parse(str, dateTimeFormatter);
    }

}
