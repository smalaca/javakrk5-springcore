package com.smalaca.messagesender.repository.inmemory;

import com.smalaca.messagesender.domain.EmailStat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MessageStatsRepositoryTest {
    @Autowired
    private MessageStatsRepository messageStatsRepository;

    @Test
    public void shouldSaveOneEmailStat() {
        EmailStat someStat = new EmailStat("id", "franek", "stefan", new Date());
        messageStatsRepository.save(someStat);
        Assert.assertEquals(1, messageStatsRepository.count());
    }

}