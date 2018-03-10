package com.smalaca.messagesender.exceptions.inmemory;

public class SecurityResponse {

    private final String error;

    public SecurityResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
