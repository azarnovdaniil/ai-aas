package ru.daniilazarnov.common.model.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonedDateTimeWriteConverter implements Converter<Long, ZonedDateTime> {
    @Override
    public ZonedDateTime convert(Long date) {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.systemDefault());
    }
}
