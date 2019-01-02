package ru.daniilazarnov.calc.property;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.daniilazarnov.common.config.GameConfig;

import java.io.File;
import java.io.IOException;

@Configuration
@ConfigurationProperties()
public class StorageProperties {

    private String gameConfigLocation;
    private String beforeCalcLocation;
    private String afterCalcLocation;
    private String csvDelimiter;
    private String dateTimeFormatter;

    public String getBeforeCalcLocation() {
        return beforeCalcLocation;
    }

    public void setBeforeCalcLocation(String beforeCalcLocation) {
        this.beforeCalcLocation = beforeCalcLocation;
    }

    public String getAfterCalcLocation() {
        return afterCalcLocation;
    }

    public void setAfterCalcLocation(String afterCalcLocation) {
        this.afterCalcLocation = afterCalcLocation;
    }

    public String getCsvDelimiter() {
        return csvDelimiter;
    }

    public void setCsvDelimiter(String csvDelimiter) {
        this.csvDelimiter = csvDelimiter;
    }

    public String getDateTimeFormatter() {
        return dateTimeFormatter;
    }

    public void setDateTimeFormatter(String dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

    public void setGameConfigLocation(String gameConfigLocation) {
        this.gameConfigLocation = gameConfigLocation;
    }

    @Bean
    public GameConfig gameConfig() throws IOException {
        return new ObjectMapper().readValue(new File(gameConfigLocation), GameConfig.class);
    }

}
