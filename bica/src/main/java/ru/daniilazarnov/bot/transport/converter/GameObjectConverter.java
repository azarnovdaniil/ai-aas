package ru.daniilazarnov.bot.transport.converter;

import org.springframework.stereotype.Component;
import ru.daniilazarnov.bot.paradigm.teleport.model.Teleport;
import ru.daniilazarnov.common.model.entity.GameObject;

@Component
public class GameObjectConverter {

    public GameObject convert(String name) {
        return new Teleport("teleport");
    }
}

