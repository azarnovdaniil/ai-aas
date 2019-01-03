package ru.daniilazarnov.bot.property;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.daniilazarnov.common.config.GameConfig;

import java.io.File;
import java.io.IOException;

@Component
@ConfigurationProperties(prefix = "paradigm")
public class ParadigmProperties {

    private String url;
    private String configLocation;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setConfigLocation(String configLocation) {
        this.configLocation = configLocation;
    }

    @Bean
    public GameConfig gameConfig() throws IOException {
        return new ObjectMapper().readValue(new File(configLocation), GameConfig.class);
    }

}
