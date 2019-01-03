package ru.daniilazarnov.calc.storage.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.daniilazarnov.calc.property.StorageProperties;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path beforeLocation;
    private final Path afterLocation;

    private static final Logger logger = LoggerFactory.getLogger(FileSystemStorageService.class);

    public FileSystemStorageService(StorageProperties properties) {
        this.beforeLocation = Paths.get(properties.getBeforeLocation());
        this.afterLocation = Paths.get(properties.getAfterLocation());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(beforeLocation, 2)
                    .filter(path -> !path.equals(beforeLocation))
                    .filter(path -> path.toString().endsWith(".csv"))
                    .filter(path -> Files.isRegularFile(path));
        } catch (IOException e) {
            logger.error(e.getMessage());
            return Stream.<Path>builder().build();
        }
    }

    @Override
    public void writeCsv(Path path, List<String> events) throws IOException {
        Path directory = afterLocation.resolve(path.getParent().getFileName());

        if (!Files.exists(directory)) {
            Files.createDirectory(directory);
        }

        Files.write(directory.resolve(path.getFileName()), events);
    }

}
