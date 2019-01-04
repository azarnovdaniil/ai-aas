package ru.daniilazarnov.bot.core.service;

import org.springframework.stereotype.Service;
import ru.daniilazarnov.bot.core.memory.MemoryDao;
import ru.daniilazarnov.common.model.entity.Action;
import ru.daniilazarnov.common.model.entity.Actor;
import ru.daniilazarnov.common.model.entity.Event;

@Service
public class MemoryServiceImpl implements MemoryService {

    private final MemoryDao memoryDao;

    public MemoryServiceImpl(MemoryDao memoryDao) {
        this.memoryDao = memoryDao;
    }

    @Override
    public Event updateMemory(Event event) {

        String sessionId = event.getSessionId();
        Actor actor = event.getActor();
        Action action = event.getOperation().getAction();
        Actor target = event.getOperation().getTarget();

        if (!memoryDao.isInitSession(sessionId)) {
            memoryDao.initSession(sessionId);
            if (actor.isNotNone()) {
                memoryDao.initMemoryForActorInSession(sessionId, actor);
            }
            if (target.isNotNone()) {
                memoryDao.initMemoryForActorInSession(sessionId, target);
            }
        }

        if (actor.isNotNone() && memoryDao.isNotDetermined(sessionId, actor)) {
            memoryDao.initMemoryForActorInSession(sessionId, actor);
        }
        if (target.isNotNone() && memoryDao.isNotDetermined(sessionId, target)) {
            memoryDao.initMemoryForActorInSession(sessionId, target);
        }

        switch (event.getOperation().getAction().getType()) {
            case NORMAL:
                memoryDao.updateAppraisal(sessionId, actor, target, action);
                break;
            case ALL:
                memoryDao.updateAllAppraisal(sessionId, actor, action);
                break;
            case SELF:
                memoryDao.updateSelfAppraisal(sessionId, actor, action);
                break;
            case SYSTEM:
            default:
                break;
        }

        event.getAppraisalStateSet().addAll(memoryDao.getAppraisalState(sessionId));

        return event;
    }

}
