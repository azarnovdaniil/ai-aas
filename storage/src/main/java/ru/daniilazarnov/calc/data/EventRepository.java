package ru.daniilazarnov.calc.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.daniilazarnov.common.model.entity.Event;

public interface EventRepository extends MongoRepository<Event, String> {
}
