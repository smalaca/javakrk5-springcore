package com.smalaca.messagesender.exceptions.inmemory;

public class UserDoesntExistException extends RuntimeException {

    public UserDoesntExistException() {
    }

    public UserDoesntExistException(String message) {
        super(message);
    }

    public UserDoesntExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDoesntExistException(Throwable cause) {
        super(cause);
    }

    public UserDoesntExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getMessage() {
        return "USER DOESN'T EXIST";
    }

}
