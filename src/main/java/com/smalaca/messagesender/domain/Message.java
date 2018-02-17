package com.smalaca.messagesender.domain;

public class Message {
    private static final String NO_VALUE = "";

    private String subject = NO_VALUE;
    private String body = NO_VALUE;
    private String from = NO_VALUE;
    private String to = NO_VALUE;
    private String id = NO_VALUE;

    public static Message aMessage(String subject, String body, String from) {
        Message message = new Message(subject, body);
        message.setFrom(from);
        return message;
    }

    public Message(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setId(String id) {
        this.id = id;
    }

    Message(MessageBuilder messageBuilder) {
        subject = messageBuilder.subject;
        body = messageBuilder.body;
        from = messageBuilder.sentFrom;
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
        if (!from.equals(message.from)) return false;
        if (!id.equals(message.id)) return false;
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

    public boolean hasSameId(String id) {
        return this.id == id;
    }

    public static class MessageBuilder {
        private String subject = NO_VALUE;
        private String body = NO_VALUE;
        private String sentFrom = NO_VALUE;
        private String sentTo = NO_VALUE;
        private String id = NO_VALUE;

        public MessageBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public MessageBuilder withSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public MessageBuilder withBody(String body) {
            this.body = body;
            return this;
        }

        public MessageBuilder withFrom(String sentFrom) {
            this.sentFrom = sentFrom;
            return this;
        }

        public MessageBuilder withTo(String sentTo) {
            this.sentTo = sentTo;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }
}
