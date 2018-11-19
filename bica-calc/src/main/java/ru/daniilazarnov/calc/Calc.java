package ru.daniilazarnov.calc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.daniilazarnov.calc.service.StorageService;

@SpringBootApplication
public class Calc {

    public static void main(String[] args) {
        SpringApplication.run(Calc.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return args -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}
