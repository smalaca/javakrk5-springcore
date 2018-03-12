package com.smalaca.messagesender.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message {
    private String subject;
    private String body;
    private String sender;
    private String to;
    @Id @GeneratedValue
    private String id;

    public Message() {
    }

    public void setId(String id) {
        this.id = id;
    }

    Message(MessageBuilder messageBuilder) {
        subject = messageBuilder.subject;
        body = messageBuilder.body;
        sender = messageBuilder.sentFrom;
        to = messageBuilder.sentTo;
        id = messageBuilder.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (!subject.equals(message.subject)) return false;
        if (!body.equals(message.body)) return false;
        if (!sender.equals(message.sender)) return false;
        if (!to.equals(message.to)) return false;
        return id != null ? id.equals(message.id) : message.id == null;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public String getSender() {
        return sender;
    }

    public String getTo() {
        return to;
    }

    public String getId() {return  id;}

    public boolean hasSameId(String id) {
        return this.id.equals(id);
    }

    static class MessageBuilder {
        private String subject;
        private String body;
        private String sentFrom;
        private String sentTo;
        private String id;

        MessageBuilder withId(String id) {
            this.id = id;
            return this;
        }

        MessageBuilder withSubject(String subject) {
            this.subject = subject;
            return this;
        }

        MessageBuilder withBody(String body) {
            this.body = body;
            return this;
        }

        MessageBuilder withFrom(String sentFrom) {
            this.sentFrom = sentFrom;
            return this;
        }

        MessageBuilder withTo(String sentTo) {
            this.sentTo = sentTo;
            return this;
        }

        Message build() {
            return new Message(this);
        }
    }
}
