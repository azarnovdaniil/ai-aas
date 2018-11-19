package ru.daniilazarnov.calc.service;

import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.daniilazarnov.calc.exception.ClientException;
import ru.daniilazarnov.calc.exception.ClientFileNotFoundException;
import ru.daniilazarnov.calc.property.CalcProperties;


import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path beforeCalcLocation;
    private final Path afterCalcLocation;

    @Autowired
    public FileSystemStorageService(CalcProperties calcProperties) {
        this.beforeCalcLocation = Paths.get(calcProperties.getBeforeCalcLocation());
        this.afterCalcLocation = Paths.get(calcProperties.getAfterCalcLocation());
    }

    @Override
    public void store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new ClientException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new ClientException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.beforeCalcLocation.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new ClientException("Failed to store file " + filename, e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.beforeCalcLocation, 1)
                    .filter(path -> !path.equals(this.beforeCalcLocation))
                    .map(this.beforeCalcLocation::relativize);
        } catch (IOException e) {
            throw new ClientException("Failed to read stored files", e);
        }

    }

    @Override
    public void createCSV(List<String> strings) {
        try (
                Writer writer = Files.newBufferedWriter(Paths.get("./string-array-sample.csv"));

                CSVWriter csvWriter = new CSVWriter(writer,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END)
        ) {
            strings.forEach(s -> csvWriter.writeNext(new String[]{s}));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Path load(String filename) {
        return beforeCalcLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new ClientFileNotFoundException(
                        "Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new ClientFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(beforeCalcLocation.toFile());
        FileSystemUtils.deleteRecursively(afterCalcLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(beforeCalcLocation);
            Files.createDirectories(afterCalcLocation);
        } catch (IOException e) {
            throw new ClientException("Could not initialize storage", e);
        }
    }
}
