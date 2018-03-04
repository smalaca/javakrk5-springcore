package com.smalaca.messagesender.domain;

import java.util.Objects;

public class User {
    private int id;
    private final String login;
    private final String email;
    private final String slack;
    private final String twitter;

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getSlack() {
        return slack;
    }

    public String getTwitter() {
        return twitter;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void blockUser(){
        isBlocked = true;
    }

    public User(UserBuilder userBuilder) {
        login = userBuilder.login;
        email = userBuilder.email;
        slack = userBuilder.slack;
        twitter = userBuilder.twitter;
    }


    static class UserBuilder {
        private String login;
        private String email;
        private String slack;
        private String twitter;

        UserBuilder withLogin(String login) {
            this.login = login;
            return this;
        }

        UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        UserBuilder withSlack(String slack) {
            this.slack = slack;
            return this;
        }

        UserBuilder withTwitter(String twitter) {
            this.twitter = twitter;
            return this;
        }

        User build() {
            return new User(this);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {

        return Objects.hash(login);
    }
}
