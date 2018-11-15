package ru.daniilazarnov.bot.paradigm.teleport.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties()
public class TeleportConfig {

    private int countPlayer;
    private List<Action> actions = new ArrayList<>();

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public int getCountPlayer() {
        return countPlayer;
    }

    public void setCountPlayer(int countPlayer) {
        this.countPlayer = countPlayer;
    }

    public List<Action> getActions() {
        return actions;
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
