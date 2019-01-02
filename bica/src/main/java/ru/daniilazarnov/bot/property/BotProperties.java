package ru.daniilazarnov.bot.property;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.daniilazarnov.common.config.GameConfig;

import java.io.File;
import java.io.IOException;

@Configuration
@ConfigurationProperties()
public class BotProperties {

    private String gameConfigLocation;
    private String clientUrl;

    public void setGameConfigLocation(String gameConfigLocation) {
        this.gameConfigLocation = gameConfigLocation;
    }

    public String getClientUrl() {
        return clientUrl;
    }

    public void setClientUrl(String clientUrl) {
        this.clientUrl = clientUrl;
    }

    @Bean
    public GameConfig gameConfig() throws IOException {
        return new ObjectMapper().readValue(new File(gameConfigLocation), GameConfig.class);
    }

}
