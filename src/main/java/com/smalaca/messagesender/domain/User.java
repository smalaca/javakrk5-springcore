package com.smalaca.messagesender.domain;

public class User {
    private int id;
    private String login;
    private String email;
    private String slack;
    private String twitter;

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
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
