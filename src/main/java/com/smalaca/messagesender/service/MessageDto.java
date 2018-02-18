package com.smalaca.messagesender.service;

public class MessageDto {
    private static final String NO_VALUE = "";

    private String body = NO_VALUE;
    private String subject = NO_VALUE;
    private String from = NO_VALUE;
    private String to = NO_VALUE;

    public String getBody() {
        return body;
    }

    public String getSubject() {
        return subject;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
