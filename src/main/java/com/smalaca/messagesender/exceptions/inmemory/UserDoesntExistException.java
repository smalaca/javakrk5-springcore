package com.smalaca.messagesender.exceptions.inmemory;

public class UserDoesntExistException extends RuntimeException {
    private final String message = "USER DOESN'T EXIST";
    private String login;

    public UserDoesntExistException(String login) {
        this.login = login;
    }

    @Override
    public String getMessage() {
        return login + " - " + message;
    }

}
