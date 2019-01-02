package ru.daniilazarnov.calc.storage;

import ru.daniilazarnov.common.model.data.Event;

import java.nio.file.Path;
import java.util.List;

public interface LogDao {

    List<Event> getListEvent(Path path);

}
