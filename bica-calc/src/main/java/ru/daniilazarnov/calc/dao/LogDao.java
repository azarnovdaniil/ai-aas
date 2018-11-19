package ru.daniilazarnov.calc.dao;

import ru.daniilazarnov.calc.domain.UnityLogRow;

import java.util.List;

public interface LogDao {

    List<UnityLogRow> getListEvent();

}
