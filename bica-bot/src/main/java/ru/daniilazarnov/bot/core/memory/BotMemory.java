package ru.daniilazarnov.bot.core.memory;

import org.springframework.stereotype.Repository;
import ru.daniilazarnov.bot.core.property.BotProperties;
import ru.daniilazarnov.bot.core.domain.Action;
import ru.daniilazarnov.bot.core.domain.Actor;
import ru.daniilazarnov.bot.core.domain.Appraisal;
import ru.daniilazarnov.bot.core.emotional.AppraisalFunctions;
import ru.daniilazarnov.bot.util.Util;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BotMemory {

    private final Map<String, Appraisal> memoryAboutActor = new HashMap<>();

    private final BotProperties botProperties;

    public BotMemory(BotProperties botProperties) {
        this.botProperties = botProperties;
    }

    public void initMemory(Actor actor) {
        memoryAboutActor.put(Util.targetKey(actor), Appraisal.valueOf(botProperties.getInitialValence(), botProperties.getInitialDominance()));
        memoryAboutActor.put(Util.actorKey(actor), Appraisal.valueOf(botProperties.getInitialValence(), botProperties.getInitialDominance()));
    }

    public void updateMemory(Action action, Actor actor) {
        memoryAboutActor.put(Util.targetKey(actor), AppraisalFunctions.targetAppraisalFunc.apply(memoryAboutActor.get(Util.targetKey(actor)), action.getAppraisal()));
        memoryAboutActor.put(Util.actorKey(actor), AppraisalFunctions.actorAppraisalFunc.apply(memoryAboutActor.get(Util.actorKey(actor)), action.getAppraisal()));
    }

    public Appraisal getAppraisal(String key) {
        return memoryAboutActor.get(key);
    }

}
