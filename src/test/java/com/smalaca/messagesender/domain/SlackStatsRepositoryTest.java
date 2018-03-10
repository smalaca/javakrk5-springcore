package com.smalaca.messagesender.domain;

import com.smalaca.messagesender.repository.inmemory.jpa.SlackStatsRepository;
import org.junit.Assert;
import org.junit.Before;
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

    @Before
    public void addSlackStats(){

        slackStatsRepository.save(new SlackStat("Janusz","Dariusz","111"));
        slackStatsRepository.save(new SlackStat("Kazek","Dariusz","165"));
        slackStatsRepository.save(new SlackStat("Mateusz","Kazek","189"));
        slackStatsRepository.save(new SlackStat("Seba","Przemek","77"));
        slackStatsRepository.save(new SlackStat("Lech","Stanisław","166"));
        slackStatsRepository.save(new SlackStat("Stanisła","Dariusz","101"));
        slackStatsRepository.save(new SlackStat("Seba","Dariusz","120"));
        slackStatsRepository.save(new SlackStat("Lech","Seba","129"));
        slackStatsRepository.save(new SlackStat("Seba","Kazek","128"));
        slackStatsRepository.save(new SlackStat("Seba","Stanisław","122"));
    }

    @Test
    public void shouldReturnThreeFirstStats() {
        String messageFrom = "Seba";
        String messageTo = "Stanisław";

        slackStatsRepository.findFirst3ByMessageFromContainsAndMessageToContainsOrderByMessageToDesc(
                messageFrom, messageTo
        );
    }



    @Test
    public void shouldNotCheckStatByIdThatNotExist() {
        Assert.assertNull(slackStatsRepository.findOne(78));
    }

    @Test
    public void shouldAddStat() {
        SlackStat stats = new SlackStat("messageId", "some id", "some other id");

        slackStatsRepository.save(stats);
        SlackStat testSlackStat = slackStatsRepository.findOne(stats.getId());

        Assert.assertEquals(stats, testSlackStat);

    }

    @Test
    public void shouldCheckStatByIdThatExist() {
        SlackStat stat = new SlackStat("messageId", "some id", "some other id");
        slackStatsRepository.save(stat);
        SlackStat slackSlackStat = slackStatsRepository.findOne(stat.getId());
        Assert.assertNotNull(slackSlackStat);
        Assert.assertEquals(slackSlackStat, stat);
    }

    @Test
    public void shouldFindAllStats() {
        SlackStat slackStat = new SlackStat("messageId", "some id", "some other id");
        slackStatsRepository.save(slackStat);
        SlackStat slackStat1 = new SlackStat("messageId1", "some id1", "some other id1");
        slackStatsRepository.save(slackStat1);
        SlackStat slackStat2 = new SlackStat("messageId2", "some id2", "some other id2");
        slackStatsRepository.save(slackStat2);

        Iterable<SlackStat> findAllStatsList = slackStatsRepository.findAll();
        List<SlackStat> lisOfAllSlackStats = new ArrayList<>();
        findAllStatsList.forEach(lisOfAllSlackStats::add);

        Assert.assertEquals(lisOfAllSlackStats.get(0), slackStat);
        Assert.assertEquals(lisOfAllSlackStats.get(1), slackStat1);
        Assert.assertEquals(lisOfAllSlackStats.get(2), slackStat2);

    }


    @Test
    public void shouldUpdateStat() {
        SlackStat stats = new SlackStat("messageId", "some id", "some other id");
        slackStatsRepository.save(stats);
        SlackStat testSlackStat = slackStatsRepository.findOne(stats.getId());
        testSlackStat.setFrom("some id update");
        slackStatsRepository.save(testSlackStat);
        testSlackStat = slackStatsRepository.findOne(testSlackStat.getId());


        Assert.assertNotEquals(stats, testSlackStat);
        Assert.assertEquals(testSlackStat.getId(), stats.getId());
        Assert.assertEquals(testSlackStat.getMessageId(), stats.getMessageId());
        Assert.assertEquals(testSlackStat.getMessageTo(), stats.getMessageTo());

    }

    @Test
    public void shouldDeleteStat() {
        SlackStat slackStat = new SlackStat("messageId", "some id", "another id");
        slackStatsRepository.save(slackStat);

        slackStatsRepository.delete(slackStat);
        Assert.assertNull(slackStatsRepository.findOne(slackStat.getId()));
    }


}
