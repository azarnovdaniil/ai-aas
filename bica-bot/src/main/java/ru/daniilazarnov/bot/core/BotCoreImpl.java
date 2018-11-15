package ru.daniilazarnov.bot.core;

import org.springframework.stereotype.Service;
import ru.daniilazarnov.bot.core.domain.Action;
import ru.daniilazarnov.bot.core.domain.Actor;
import ru.daniilazarnov.bot.core.domain.Operation;
import ru.daniilazarnov.bot.core.emotional.EmotionalMind;
import ru.daniilazarnov.bot.core.memory.BotMemory;

import java.util.Collection;
import java.util.List;

@Service
public class BotCoreImpl implements BotCore {

    private final EmotionalMind emotionalMind;
    private final BotMemory botMemory;

    public BotCoreImpl(EmotionalMind emotionalMind, BotMemory botMemory) {
        this.emotionalMind = emotionalMind;
        this.botMemory = botMemory;
    }

    @Override
    public void initMemory(Collection<Actor> actors) {
        actors.forEach(botMemory::initMemory);
    }

    @Override
    public void updateMemory(Action action, Actor actor) {
        botMemory.updateMemory(action, actor);
    }

    @Override
    public Operation actionHandle(List<Operation> operations) {
        return emotionalMind.chooseOperation(operations);
    }

}
