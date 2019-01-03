package ru.daniilazarnov.calc.property;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.daniilazarnov.common.config.GameConfig;

import java.io.File;
import java.io.IOException;

@Component
@ConfigurationProperties(prefix = "parser")
public class ParserProperties {

    private String configLocation;
    private String delimiter;
    private String formatter;
    private String botUrl;

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public String getBotUrl() {
        return botUrl;
    }

    public void setBotUrl(String botUrl) {
        this.botUrl = botUrl;
    }

    public void setConfigLocation(String configLocation) {
        this.configLocation = configLocation;
    }

    @Bean
    public GameConfig gameConfig() throws IOException {
        return new ObjectMapper().readValue(new File(configLocation), GameConfig.class);
    }

}
