package ru.daniilazarnov.calc.domain;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

class EventTest {

    @Test
    public void parseTime() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date parsedDate = dateFormat.parse("2018-04-04 11:02:59.446 +03:00");


        LocalDateTime localDateTime = parsedDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();


        System.out.println(localDateTime);
        System.out.println(parsedDate);
    }

}