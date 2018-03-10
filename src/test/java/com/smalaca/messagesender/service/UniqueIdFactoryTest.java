package com.smalaca.messagesender.service;

import org.junit.Assert;
import org.junit.Test;

public class UniqueIdFactoryTest {

    @Test
    public void shouldConfirmTwoIdentifiersAreDifferent() {

        String firstId = new UniqueIdFactory().genarateId();
        String secondId = new UniqueIdFactory().genarateId();

        Assert.assertFalse(firstId.equals(secondId));
    }
}