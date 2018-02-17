package com.smalaca.messagesender.domain;

public class Message {
    private final String subject;
    private final String body;
    private final String from;
    private final String to;

    public Message(MessageBuilder messageBuilder) {
        subject = messageBuilder.subject;
        body = messageBuilder.body;
        from = messageBuilder.sentFrom;
        to = messageBuilder.sentTo;
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

    public static class MessageBuilder {
        private String subject;
        private String body;
        private String sentFrom;
        private String sentTo;

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
