package ru.daniilazarnov.web.exception;

public class ClientFileNotFoundException extends ClientException {

    public ClientFileNotFoundException(String message) {
        super(message);
    }

    public ClientFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}