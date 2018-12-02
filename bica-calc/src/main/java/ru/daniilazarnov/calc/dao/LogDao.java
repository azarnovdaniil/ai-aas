package ru.daniilazarnov.calc.dao;

import ru.daniilazarnov.calc.domain.Event;

import java.nio.file.Path;
import java.util.List;

public interface LogDao {

    List<Event> getListEvent(Path path);

}
