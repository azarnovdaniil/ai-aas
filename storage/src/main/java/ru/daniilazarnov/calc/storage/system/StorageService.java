package ru.daniilazarnov.calc.storage.system;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface StorageService {

    Stream<Path> loadAll();

    void writeCsv(Path path, List<String> events) throws IOException;

    void writeJson(Path path, String game);

}
