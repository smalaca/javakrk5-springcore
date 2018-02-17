package com.smalaca.messagesender.domain;

public interface MessageRepository {

    boolean exists(String id);

    void add();
}
