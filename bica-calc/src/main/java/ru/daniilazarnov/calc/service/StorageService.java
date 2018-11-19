package ru.daniilazarnov.calc.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    void store(MultipartFile file);

    Stream<Path> loadAll();

    void createCSV(List<String> strings);

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

}
