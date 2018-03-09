package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.IdFactory;

import java.util.UUID;

public class UniqueIdFactory implements IdFactory{

    @Override
    public String genarateId() {
        return UUID.randomUUID().toString();
    }
}
