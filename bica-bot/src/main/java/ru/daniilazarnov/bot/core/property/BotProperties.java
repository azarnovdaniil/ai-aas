package ru.daniilazarnov.bot.core.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("core")
public class BotProperties {

    private double initialDominance;
    private double initialValence;

    public double getInitialDominance() {
        return initialDominance;
    }

    public double getInitialValence() {
        return initialValence;
    }

    public void setInitialDominance(double initialDominance) {
        this.initialDominance = initialDominance;
    }

    public void setInitialValence(double initialValence) {
        this.initialValence = initialValence;
    }

}
