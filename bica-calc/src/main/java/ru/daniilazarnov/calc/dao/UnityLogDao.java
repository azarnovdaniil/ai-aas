package ru.daniilazarnov.calc.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.daniilazarnov.calc.converter.LogConverter;
import ru.daniilazarnov.calc.model.Event;
import ru.daniilazarnov.calc.model.UnityLogRow;
import ru.daniilazarnov.calc.property.CalcProperties;

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
    private final LogConverter logConverter;

    private static final Logger logger = LoggerFactory.getLogger(UnityLogDao.class);

    public UnityLogDao(CalcProperties properties, LogConverter logConverter) {
        this.csvDelimiter = properties.getCsvDelimiter();
        this.logConverter = logConverter;
    }

    @Override
    public List<Event> getListEvent(Path path) {
        return getUnityLogList(path).stream()
                .map(logConverter::logToEvent)
                .collect(Collectors.toList());
    }

    private List<UnityLogRow> getUnityLogList(Path path) {

        List<UnityLogRow> unityLogRows = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(csvDelimiter);
                UnityLogRow unityLogRow = UnityLogRow.valueOf(row);

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
