package ru.daniilazarnov.calc.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties()
public class CalcProperties {

    private double initialDominance;
    private double initialValence;
    private List<Action> actions = new ArrayList<>();
    private List<String> systemActions = new ArrayList<>();
    private String beforeCalcLocation;

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

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public List<Action> getActions() {
        return actions;
    }

    public List<String> getSystemActions() {
        return systemActions;
    }

    public void setSystemActions(List<String> systemActions) {
        this.systemActions = systemActions;
    }

    public static class Action {
        private String name;
        private double valence;
        private double dominance;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getValence() {
            return valence;
        }

        public void setValence(double valence) {
            this.valence = valence;
        }

        public double getDominance() {
            return dominance;
        }

        public void setDominance(double dominance) {
            this.dominance = dominance;
        }
    }

}
