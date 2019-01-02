package ru.daniilazarnov.calc.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.daniilazarnov.calc.storage.converter.EventConverter;
import ru.daniilazarnov.calc.property.StorageProperties;
import ru.daniilazarnov.common.model.Event;
import ru.daniilazarnov.common.model.UnityLogRow;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class UnityLogDao implements LogDao {

    private final String csvDelimiter;
    private final EventConverter<UnityLogRow> eventConverter;

    private static final Logger logger = LoggerFactory.getLogger(UnityLogDao.class);

    public UnityLogDao(StorageProperties properties, EventConverter<UnityLogRow> eventConverter) {
        this.csvDelimiter = properties.getCsvDelimiter();
        this.eventConverter = eventConverter;
    }

    @Override
    public List<Event> getListEvent(Path path) {
        try (Stream<String> lines = Files.lines(path)) {
            return lines
                    .map(line -> line.split(csvDelimiter))
                    .map(UnityLogRow::new)
                    .filter(unityLogRow -> unityLogRow.getSessionId() != null)
                    .map(eventConverter::logToEvent)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            logger.error(e.getMessage());
            return List.of();
        }
    }

}
