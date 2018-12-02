package ru.daniilazarnov.calc.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.daniilazarnov.calc.converter.EventConverter;
import ru.daniilazarnov.calc.property.CalcProperties;
import ru.daniilazarnov.common.model.Event;
import ru.daniilazarnov.common.model.UnityLogRow;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UnityLogDao implements LogDao {

    private final String csvDelimiter;
    private final EventConverter<UnityLogRow> eventConverter;

    private static final Logger logger = LoggerFactory.getLogger(UnityLogDao.class);

    public UnityLogDao(CalcProperties properties, EventConverter<UnityLogRow> eventConverter) {
        this.csvDelimiter = properties.getCsvDelimiter();
        this.eventConverter = eventConverter;
    }

    @Override
    public List<Event> getListEvent(Path path) {
        return getUnityLogList(path).stream()
                .map(eventConverter::logToEvent)
                .collect(Collectors.toList());
    }

    private List<UnityLogRow> getUnityLogList(Path path) {

        List<UnityLogRow> unityLogRows = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(csvDelimiter);
                UnityLogRow unityLogRow = new UnityLogRow(row);

                if (unityLogRow.getSessionId() != null) {
                    unityLogRows.add(unityLogRow);
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            return List.of();
        }
        return unityLogRows;
    }

}
