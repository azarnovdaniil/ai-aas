package ru.daniilazarnov.calc.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "storage")
public class StorageProperties {

    private String beforeLocation;
    private String afterLocation;

    public String getBeforeLocation() {
        return beforeLocation;
    }

    public void setBeforeLocation(String beforeLocation) {
        this.beforeLocation = beforeLocation;
    }

    public String getAfterLocation() {
        return afterLocation;
    }

    public void setAfterLocation(String afterLocation) {
        this.afterLocation = afterLocation;
    }

}
