package com.smalaca.messagesender.repository.inmemory;

import com.smalaca.messagesender.domain.EmailStat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MessageStatsRepositoryTest {
    @Autowired
    private MessageStatsRepository messageStatsRepository;

    @Test
    public void shouldSaveTwoEmailStats() {
        Timestamp d1 = new Timestamp(System.currentTimeMillis());
        Timestamp d2 = new Timestamp(d1.getTime() + 1000);
        EmailStat someStat = new EmailStat("franek", "stefan", "subject", d1);
        EmailStat someStat2 = new EmailStat("stefan", "franek", "subject2", d2);
        List<EmailStat> stats = new ArrayList<>();
        stats.add(someStat);
        stats.add(someStat2);
        messageStatsRepository.save(someStat);
        messageStatsRepository.save(someStat2);

        Assert.assertEquals(2, messageStatsRepository.count());
        Assert.assertArrayEquals(stats.toArray(), (messageStatsRepository.findAll()).toArray());
    }

    @Test
    public void shouldFindMessageStatById() {
        Timestamp d1 = new Timestamp(System.currentTimeMillis());
        EmailStat someStat = new EmailStat("franek", "stefan", "some subject", d1);
        messageStatsRepository.save(someStat);
        messageStatsRepository.save(someStat);
        messageStatsRepository.save(someStat);
        EmailStat newStat = messageStatsRepository.findById("1");

        Assert.assertNotNull(messageStatsRepository.findById("1"));
        Assert.assertEquals(newStat, someStat);
    }

    @Test
    public void shouldFindOneMessageWithinTime() {
        Timestamp d1 = new Timestamp(System.currentTimeMillis());
        Timestamp d2 = new Timestamp(d1.getTime() + 3600 * 5 * 1000L);
        EmailStat someStat = new EmailStat("franek", "stefan", "some subject 1", new Timestamp(d1.getTime()));
        EmailStat someStat2 = new EmailStat("stefan", "franek", "some subject 2", new Timestamp(d2.getTime()));
        messageStatsRepository.save(someStat);
        messageStatsRepository.save(someStat2);
        d1.setTime(d1.getTime());
        d2.setTime(d2.getTime() + 1);

        List<EmailStat> myList = messageStatsRepository.findAllByDateAfterAndDateBefore(d1, d2);

        Assert.assertEquals(1, myList.size());
        Assert.assertEquals(someStat2, myList.get(0));
    }

    @Test
    public void shouldFindMessageWithName() {
        EmailStat someStat = new EmailStat("franek", "stefan", "some subject 1", new Timestamp(System.currentTimeMillis()));
        messageStatsRepository.save(someStat);

        Optional<String> retrivedId = messageStatsRepository.findIdByName("franek");

        Assert.assertEquals("1", retrivedId.get());
    }


}