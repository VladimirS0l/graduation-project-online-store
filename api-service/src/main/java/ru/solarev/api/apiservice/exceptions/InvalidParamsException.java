package ru.solarev.api.apiservice.exceptions;

public class InvalidParamsException extends RuntimeException {
    public InvalidParamsException(String message) {
        super(message);
    }
}
