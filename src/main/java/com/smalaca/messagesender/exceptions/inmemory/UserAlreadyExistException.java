package com.smalaca.messagesender.exceptions.inmemory;

public class UserAlreadyExistException extends RuntimeException {


    private final String login;

    public UserAlreadyExistException(String login) {
        this.login = login;
    }

    @Override
    public String getMessage() {
        return "User with login "+login+" already exist in repository";
    }


}
