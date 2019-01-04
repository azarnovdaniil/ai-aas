package ru.daniilazarnov.bot.core.emotional;

import org.springframework.stereotype.Service;
import ru.daniilazarnov.bot.core.memory.MemoryDao;
import ru.daniilazarnov.bot.core.rational.RationalService;
import ru.daniilazarnov.common.model.entity.Actor;
import ru.daniilazarnov.common.model.entity.Appraisal;
import ru.daniilazarnov.common.model.entity.Operation;
import ru.daniilazarnov.common.model.entity.State;

import java.util.*;

import static ru.daniilazarnov.bot.core.emotional.functions.AppraisalFunctions.likelyHoodFunc;

@Service
public class EmotionalActionServiceImpl implements EmotionalActionService {

    private static final double MIN_LIKELY_HOOD = 0.01;

    private final Random random = new Random();
    private final MemoryDao memoryDao;
    private final RationalService rationalService;

    public EmotionalActionServiceImpl(MemoryDao memoryDao, RationalService rationalService) {
        this.memoryDao = memoryDao;
        this.rationalService = rationalService;
    }

    @Override
    public Operation chooseOperation(String sessionId, Actor actor) {
        List<Operation> operations = new ArrayList<>(rationalService.getOperations(sessionId, actor));

        if (operations.isEmpty() || !memoryDao.isInitSession(sessionId)) {
            return Operation.none();
        }

        Map<Operation, Double> operationAndValue = new HashMap<>();

        double sumLikelyHood = 0.0;

        Map<Actor, State> actorState = new HashMap<>();
        Map<Actor, State> targetState = new HashMap<>();

        for (State state : memoryDao.getAppraisalState(sessionId)) {
            if (state.getActor().equals(actor)) {
                actorState.put(state.getTarget(), state);
            }
            if (state.getTarget().equals(actor)) {
                targetState.put(state.getActor(), state);
            }
        }

        for (Operation operation : operations) {
            Appraisal actorAppraisal = actorState.get(operation.getTarget()).getAppraisal();
            Appraisal targetAppraisal = targetState.get(operation.getTarget()).getAppraisal();

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
