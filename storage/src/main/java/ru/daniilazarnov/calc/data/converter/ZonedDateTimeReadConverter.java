package ru.daniilazarnov.calc.data.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.ZonedDateTime;

public class ZonedDateTimeReadConverter implements Converter<ZonedDateTime, Long> {

    @Override
    public Long convert(ZonedDateTime zonedDateTime) {
        return zonedDateTime.toInstant().toEpochMilli();
    }
}
