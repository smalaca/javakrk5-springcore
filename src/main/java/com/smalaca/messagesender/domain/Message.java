package com.smalaca.messagesender.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message {
    private String subject;
    private String body;
    @Column(name = "sentfrom")
    private String from;
    @Column(name = "sentto")
    private String to;
    @Id @GeneratedValue
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public Message() {
    }

    Message(MessageBuilder messageBuilder) {
        subject = messageBuilder.subject;
        body = messageBuilder.body;
        from = messageBuilder.sentFrom;
        to = messageBuilder.sentTo;
//        id = messageBuilder.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (!subject.equals(message.subject)) return false;
        if (!body.equals(message.body)) return false;
        if (!from.equals(message.from)) return false;
        if (!id.equals(message.id) && id != "") return false;
        return to.equals(message.to);
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public String getFrom() {
        return from;
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
