package ru.daniilazarnov.bot.core.rational;

import org.springframework.stereotype.Service;
import ru.daniilazarnov.common.model.Actor;
import ru.daniilazarnov.common.model.Operation;

import java.util.ArrayList;
import java.util.List;

@Service
public class RationalServiceImpl implements RationalService {

    private final List<Operation> operations = new ArrayList<>();

    @Override
    public List<Operation> getOperations(String sessionId, Actor actor) {
        return operations;
    }

    @Override
    public void addOperation(String sessionId, Actor actor, Operation operation) {
        operations.add(operation);
    }

}
