package com.smalaca.messagesender.service;

public class UserDto {
    private static final String NO_VALUE = "";

    private String login = NO_VALUE;
    private String email = NO_VALUE;
    private String slack = NO_VALUE;
    private String twitter = NO_VALUE;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSlack() {
        return slack;
    }

    public void setSlack(String slack) {
        this.slack = slack;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }
}
