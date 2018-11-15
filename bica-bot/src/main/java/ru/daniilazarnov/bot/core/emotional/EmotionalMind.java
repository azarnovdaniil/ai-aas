package ru.daniilazarnov.bot.core.emotional;

import org.springframework.stereotype.Service;
import ru.daniilazarnov.bot.core.domain.Appraisal;
import ru.daniilazarnov.bot.core.domain.Operation;
import ru.daniilazarnov.bot.util.Util;
import ru.daniilazarnov.bot.core.memory.BotMemory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static ru.daniilazarnov.bot.core.emotional.AppraisalFunctions.likelyHoodFunc;

@Service
public class EmotionalMind {

    private final BotMemory botMemory;
    private final Random random = new Random();
    private static final double MIN_LIKELY_HOOD = 0.01;

    public EmotionalMind(BotMemory botMemory) {
        this.botMemory = botMemory;
    }

    public Operation chooseOperation(List<Operation> operations) {

        Map<Operation, Double> operationAndValue = new HashMap<>();

        double sumLikelyHood = 0.0;

        for (Operation operation : operations) {
            Appraisal actorAppraisal = botMemory.getAppraisal(Util.actorKey(operation.getTarget()));
            Appraisal targetAppraisal = botMemory.getAppraisal(Util.targetKey(operation.getTarget()));

            Double likelyHood = likelyHoodFunc.apply(operation.getAction().getAppraisal(), actorAppraisal, targetAppraisal);

            operationAndValue.put(operation, likelyHood);
            sumLikelyHood += likelyHood;
        }

        double randomValue = MIN_LIKELY_HOOD + (sumLikelyHood - MIN_LIKELY_HOOD) * random.nextDouble();

        double predValue = 0.0;

        Operation resultOperation = operations.get(0);

        for (Map.Entry<Operation, Double> entry : operationAndValue.entrySet()) {

            if (randomValue < predValue + entry.getValue()) {
                resultOperation = entry.getKey();
                break;
            } else {
                predValue += entry.getValue();
            }
        }

        return resultOperation;
    }

}
