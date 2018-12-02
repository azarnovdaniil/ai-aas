package ru.daniilazarnov.calc.model;

public enum ActionType {
    SYSTEM,
    NORMAL,
    SELF,
    ALL;

    public static ActionType of(String name) {
        switch (name) {
            case "NORMAL":
                return NORMAL;
            case "SYSTEM":
                return SYSTEM;
            case "SELF":
                return SELF;
            case "ALL":
                return ALL;
            default:
                return NORMAL;
        }
    }
}
