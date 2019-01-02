package ru.daniilazarnov.bot.core.rational;

import org.springframework.stereotype.Service;
import ru.daniilazarnov.common.model.Actor;
import ru.daniilazarnov.common.model.Operation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RationalServiceImpl implements RationalService {

    private final Map<String, Set<Operation>> allOperations = new HashMap<>();

    @Override
    public Set<Operation> getOperations(String sessionId, Actor actor) {
        return allOperations.get(sessionId)
                .stream()
                .filter(o -> !o.getTarget().equals(actor))
                .collect(Collectors.toSet());
    }

    @Override
    public void addOperations(String sessionId, Set<Operation> operations) {
        if (allOperations.containsKey(sessionId)) {
            allOperations.get(sessionId).addAll(operations);
        } else {
            allOperations.put(sessionId, operations);
        }
    }

}
