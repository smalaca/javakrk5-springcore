package com.smalaca.messagesender.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SlackStatsRepositoryTest {
    @Autowired
    SlackStatsRepository slackStatsRepository;

    @Test
    public void shouldNotCheckStatByIdThatNotExist() {
        Assert.assertNull(slackStatsRepository.findOne(78));
    }

    @Test
    public void shouldAddStat() {
        Stat stats = new Stat("messageId", "some id", "some other id");

        slackStatsRepository.save(stats);
        Stat testStat = slackStatsRepository.findOne(stats.getId());

        Assert.assertEquals(stats, testStat);

    }

    @Test
    public void shouldCheckStatByIdThatExist() {
        Stat stat = new Stat("messageId", "some id", "some other id");
        slackStatsRepository.save(stat);
        Stat slackStat = slackStatsRepository.findOne(stat.getId());
        Assert.assertNotNull(slackStat);
        Assert.assertEquals(slackStat, stat);
    }

    @Test
    public void shouldFindAllStats() {
        Stat stat = new Stat("messageId", "some id", "some other id");
        slackStatsRepository.save(stat);
        Stat stat1 = new Stat("messageId1", "some id1", "some other id1");
        slackStatsRepository.save(stat1);
        Stat stat2 = new Stat("messageId2", "some id2", "some other id2");
        slackStatsRepository.save(stat2);

        Iterable<Stat> findAllStatsList = slackStatsRepository.findAll();
        List<Stat> lisOfAllStats = new ArrayList<>();
        findAllStatsList.forEach(lisOfAllStats::add);

        Assert.assertEquals(lisOfAllStats.get(0), stat);
        Assert.assertEquals(lisOfAllStats.get(1), stat1);
        Assert.assertEquals(lisOfAllStats.get(2), stat2);

    }


    @Test
    public void shouldUpdateStat() {
        Stat stats = new Stat("messageId", "some id", "some other id");
        slackStatsRepository.save(stats);
        Stat testStat = slackStatsRepository.findOne(stats.getId());
        testStat.setFrom("some id update");
        slackStatsRepository.save(testStat);
        testStat = slackStatsRepository.findOne(testStat.getId());


        Assert.assertNotEquals(stats, testStat);
        Assert.assertEquals(testStat.getId(), stats.getId());
        Assert.assertEquals(testStat.getMessageId(), stats.getMessageId());
        Assert.assertEquals(testStat.getMessageTo(), stats.getMessageTo());

    }

    @Test
    public void shouldDeleteStat() {
        Stat stat = new Stat("messageId", "some id", "another id");
        slackStatsRepository.save(stat);

        slackStatsRepository.delete(stat);
        Assert.assertNull(slackStatsRepository.findOne(stat.getId()));
    }

}
