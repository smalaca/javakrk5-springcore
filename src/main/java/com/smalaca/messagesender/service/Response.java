package com.smalaca.messagesender.service;

public class Response {
    private static final boolean SUCCESS = true;
    private static final boolean FAILURE = false;

    private final boolean success;
    private final String message;

    private Response(boolean isSuccess, String message) {
        this.success = isSuccess;
        this.message = message;
    }

    static Response aSuccessfulResponseWith(String message) {
        return new Response(SUCCESS, message);
    }

    static Response aFailureResponse(String message) {
        return new Response(FAILURE, message);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
