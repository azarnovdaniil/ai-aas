package ru.daniilazarnov.bot.transport.converter;

import org.springframework.stereotype.Component;
import ru.daniilazarnov.common.model.dto.OperationTO;
import ru.daniilazarnov.common.model.entity.Action;
import ru.daniilazarnov.common.model.entity.Actor;
import ru.daniilazarnov.common.model.entity.GameObject;
import ru.daniilazarnov.common.model.entity.Operation;

@Component
public class OperationConverter {

    private final ActionConverter actionConverter;
    private final GameObjectConverter gameObjectConverter;

    public OperationConverter(ActionConverter actionConverter, GameObjectConverter gameObjectConverter) {
        this.actionConverter = actionConverter;
        this.gameObjectConverter = gameObjectConverter;
    }

    public Operation convert(OperationTO operationTO) {
        Action action = actionConverter.convert(operationTO.getAction());

        Actor actor = operationTO.getTarget() != null ? Actor.valueOf(operationTO.getTarget()) : Actor.none();

        GameObject gameObject = gameObjectConverter.convert(operationTO.getGameObject());

        return Operation.of(action, actor, gameObject, operationTO.getMultiValues());
    }

    public OperationTO convert(Operation operation) {
        String target = operation.getTarget().getName();
        String action = operation.getAction().getName();
        String gameObject = operation.getGameObject().getName();

        return new OperationTO(action, target, gameObject, operation.getMultiValues());
    }
}
