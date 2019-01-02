package ru.daniilazarnov.calc.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties()
public class CalcProperties {

    private String beforeCalcLocation;
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

    private String afterCalcLocation;

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

}
