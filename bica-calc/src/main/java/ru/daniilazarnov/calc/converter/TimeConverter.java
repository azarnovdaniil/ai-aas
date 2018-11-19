package ru.daniilazarnov.calc.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;

public final class TimeConverter {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");

    private TimeConverter() {
    }

    public static LocalDateTime stringToDate(String str) throws ParseException {
        return FORMAT.parse(str)
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

    }

}
