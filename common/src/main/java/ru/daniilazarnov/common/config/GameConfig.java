package ru.daniilazarnov.common.config;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ru.daniilazarnov.common.config.serialization.GameConfigDeserializer;
import ru.daniilazarnov.common.model.Action;
import ru.daniilazarnov.common.model.Appraisal;

import java.util.ArrayList;
import java.util.List;

@JsonDeserialize(using = GameConfigDeserializer.class)
public class GameConfig {

    private final Appraisal initialAppraisal;
    private final List<Action> actions;
    private final List<String> systemActions;

    public List<Action> getActions() {
        return actions;
    }

    public List<String> getSystemActions() {
        return systemActions;
    }

    private GameConfig(Appraisal initialAppraisal, List<Action> actions, List<String> systemActions) {
        this.initialAppraisal = initialAppraisal;
        this.actions = actions;
        this.systemActions = systemActions;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Appraisal getInitialAppraisal() {
        return initialAppraisal;
    }

    public static class Builder {

        private Appraisal initialAppraisal = Appraisal.zero();
        private List<Action> actions = new ArrayList<>();
        private List<String> systemActions = new ArrayList<>();

        Builder() {
            // private constructor
        }

        public Builder setInitialAppraisal(Appraisal initialAppraisal) {
            this.initialAppraisal = initialAppraisal;
            return this;
        }

        public Builder setActions(List<Action> actions) {
            this.actions = actions;
            return this;
        }

        public Builder setSystemActions(List<String> systemActions) {
            this.systemActions = systemActions;
            return this;
        }

        public GameConfig build() {
            return new GameConfig(initialAppraisal, actions, systemActions);
        }

    }
}
