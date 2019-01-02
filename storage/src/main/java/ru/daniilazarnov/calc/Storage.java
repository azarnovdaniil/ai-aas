package ru.daniilazarnov.calc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.daniilazarnov.bot.core.memory.BotMemoryDao;
import ru.daniilazarnov.bot.core.service.MemoryService;
import ru.daniilazarnov.bot.core.service.MemoryServiceImpl;
import ru.daniilazarnov.common.config.GameConfig;

@SpringBootApplication
public class Storage {

    public static void main(String[] args) {
        SpringApplication.run(Storage.class, args);
    }

    @Bean
    public MemoryService memoryService(GameConfig gameConfig) {
        return new MemoryServiceImpl(new BotMemoryDao(gameConfig));
    }

}
