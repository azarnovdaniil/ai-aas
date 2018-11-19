package ru.daniilazarnov.bot.transport.dao;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Repository;
import ru.daniilazarnov.bot.Bot;
import ru.daniilazarnov.bot.transport.domain.Event;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Repository
public class CsvEventDao implements EventDao {

    public List<Event> getListEvent() {

        try (Reader reader = new InputStreamReader(Bot.class.getClassLoader().getResourceAsStream("test_log.csv"))) {

            CsvToBean<Event> csvToBean = new CsvToBeanBuilder<Event>(reader)
                    .withType(Event.class)
                    .withSeparator(',')
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();

        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

}
