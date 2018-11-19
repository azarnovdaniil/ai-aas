package ru.daniilazarnov.calc.domain;

import com.opencsv.CSVWriter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;

public class Event {

    private LocalDateTime timeStamp;
    private String sessionId;
    private String participantId;
    private Action action;
    private Optional<Actor> target = Optional.empty();
    private Optional<Actor> actor = Optional.empty();
    private Map<String, String> multiValueMap = new HashMap<>();

    //2018-04-04 11:02:59.446 +03:00


    private UnityLogRow unityLogRow;
    private String appraisalState;

    //FIXME временное решение
    public Event(UnityLogRow unityLogRow, String appraisalState) {
        this.unityLogRow = unityLogRow;
        this.appraisalState = appraisalState;
    }

    public String toCSV() {
        StringJoiner joiner = new StringJoiner(String.valueOf(CSVWriter.DEFAULT_SEPARATOR));
        joiner.add(unityLogRow.toCSV())
                .add(appraisalState);

        return joiner.toString();
    }

}
