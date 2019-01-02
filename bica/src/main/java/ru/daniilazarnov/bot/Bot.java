package ru.daniilazarnov.bot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.daniilazarnov.common.config.GameConfig;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class Bot {

    private static final String GAME_CONFIG_PATH = "/Users/daniilazarnov/config/game_config.json";

    public static void main(String[] args) {
        SpringApplication.run(Bot.class, args);
    }

    @Bean
    public GameConfig gameConfig() throws IOException {
        return new ObjectMapper().readValue(new File(GAME_CONFIG_PATH), GameConfig.class);
    }

}
