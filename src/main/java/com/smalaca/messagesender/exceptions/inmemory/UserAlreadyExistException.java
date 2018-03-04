package com.smalaca.messagesender.exceptions.inmemory;

public class UserAlreadyExistException extends RuntimeException {


    public UserAlreadyExistException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "User already exist in repository";
    }


}
