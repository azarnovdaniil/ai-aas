package ru.daniilazarnov.bot.core.rational;

import org.springframework.stereotype.Service;
import ru.daniilazarnov.bot.core.memory.BotMemory;

@Service
public class RationalMind {

    private final BotMemory botMemory;

    public RationalMind(BotMemory botMemory) {
        this.botMemory = botMemory;
    }
}
