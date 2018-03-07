package com.smalaca.messagesender.exceptions.inmemory;

public class InvalidUserPassed extends RuntimeException {

    @Override
    public String getMessage() {
        return "User should have one of : mail,slack or twitter";
    }
}
