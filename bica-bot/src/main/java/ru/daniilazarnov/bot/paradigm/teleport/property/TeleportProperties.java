package ru.daniilazarnov.bot.paradigm.teleport.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.daniilazarnov.common.property.Action;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties()
public class TeleportProperties {

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

}
