package com.smalaca.messagesender.exceptions.inmemory;

public class UserAlreadyExistException extends RuntimeException {
    private final String userName;

    public UserAlreadyExistException(String userName) {
        this.userName = userName;
    }

    @Override
    public String getMessage() {
        return "User" + userName + "  already exist in repository";
    }


}
