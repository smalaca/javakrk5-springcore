package com.smalaca.messagesender.domain;

import com.sun.org.glassfish.external.statistics.Stats;
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


}

/*
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SlackStatsRepositoryTest {

    @Autowired
    SlackStatsRepository slackStatsRepository;

    @Test
    public void shouldNotCheckStatByIdThatNotExist() {
        Assert.assertNull(slackStatsRepository.findOne(78));
    }

    @Test
    public void shouldAddStat() {
        Stats stats = new Stats("messageId", "some id", "some other id");
        slackStatsRepository.save(stats);
        Stats testStat = slackStatsRepository.findOne(stats.getId());
        Assert.assertEquals(stats, testStat);
    }

    @Test
    public void shouldCheckStatByIdThatExist() {
        Stats stat = new Stats("messageId", "some id", "some other id");
        slackStatsRepository.save(stat);
        Stats slackStat = slackStatsRepository.findOne(stat.getId());
        Assert.assertNotNull(slackStat);
        Assert.assertEquals(slackStat, stat);
    }

    @Test
    public void shouldFindAllStats(){
        List<Stats> testListOfStats = addSomeStatsToRepo();
        List<Stats> listOfStats = new ArrayList<>();
        Iterable<Stats> stats = slackStatsRepository.findAll();
        stats.forEach(listOfStats::add);

        for (int i = 0; i < listOfStats.size(); i++) {
            Assert.assertEquals(listOfStats.get(i), testListOfStats.get(i));
        }
    }

    private List<Stats> addSomeStatsToRepo() {
        List<Stats> listOfStats = new ArrayList<>();
        for (int i = 0; i < 41; i++) {
            listOfStats.add(new Stats("id".join(String.valueOf(i)), "some id", "some other id" ));
        }
        return listOfStats;
    }


}
*/

