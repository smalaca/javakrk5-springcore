package com.smalaca.messagesender.service;

import org.junit.Assert;
import org.junit.Test;

import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.*;

public class UniqueIdFactoryTest {

    @Test
    public void shouldConfirmTwoIdentifiersAreDifferent() {

        String firstId = new UniqueIdFactory().genarateId();
        String secondId = new UniqueIdFactory().genarateId();

        Assert.assertFalse(firstId.equals(secondId));
    }
}