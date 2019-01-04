package ru.daniilazarnov.calc.storage.serialization;

import org.springframework.stereotype.Service;
import ru.daniilazarnov.calc.storage.serialization.csv.CsvEventSerializer;
import ru.daniilazarnov.common.model.entity.Event;

@Service
public class CalcSerializer implements Serializer {

    private final CsvEventSerializer csvEventSerializer;

    public CalcSerializer(CsvEventSerializer csvEventSerializer) {
        this.csvEventSerializer = csvEventSerializer;
    }

    @Override
    public String toCSV(Event event) {
        return csvEventSerializer.serialize(event);
    }

}
