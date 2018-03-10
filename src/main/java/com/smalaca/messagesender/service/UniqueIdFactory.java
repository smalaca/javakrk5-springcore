package com.smalaca.messagesender.service;

import java.util.UUID;

public class UniqueIdFactory implements IdFactory{

    @Override
    public String genarateId() {
        return UUID.randomUUID().toString();
    }
}
