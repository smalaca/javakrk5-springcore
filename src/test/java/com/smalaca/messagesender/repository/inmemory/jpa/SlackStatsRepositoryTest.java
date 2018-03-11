package com.smalaca.messagesender.repository.inmemory.jpa;

import com.smalaca.messagesender.domain.MessageFactory;
import com.smalaca.messagesender.domain.SlackStat;
import com.smalaca.messagesender.service.MessageDto;
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
    public void addSlackStats() {
        MessageDto messageDto = new MessageDto();
        messageDto.setSubject("subject");
        messageDto.setBody("body");
        messageDto.setFrom("seba");
        messageDto.setTo("andi");


        slackStatsRepository.save(new SlackStat(new MessageFactory().createWithoutId(messageDto)));
        slackStatsRepository.save(new SlackStat(new MessageFactory().createWithoutId(messageDto)));
        slackStatsRepository.save(new SlackStat(new MessageFactory().createWithoutId(messageDto)));
        slackStatsRepository.save(new SlackStat(new MessageFactory().createWithoutId(messageDto)));
        slackStatsRepository.save(new SlackStat(new MessageFactory().createWithoutId(messageDto)));
    }

  /*  @Test
    public void shouldReturnThreeFirstStats() {
        String messageFrom = "Seba";
        String messageTo = "andi";

        List<SlackStat> listFirstThree = slackStatsRepository.findFirst3ByMessageSentFromContainsAndMessageMessageToContainsOrderByMessageToDesc(messageFrom, messageTo);

        Assert.assertEquals(listFirstThree.size(), 3);
    }*/


    @Test
    public void shouldNotCheckStatByIdThatNotExist() {
        Assert.assertNull(slackStatsRepository.findOne(100));
    }

    @Test
    public void shouldAddStat() {
        MessageDto messageDto = new MessageDto();
        SlackStat stats = new SlackStat(new MessageFactory().createWithoutId(messageDto));

        slackStatsRepository.save(stats);
        SlackStat testSlackStat = slackStatsRepository.findOne(stats.getStatId());

        Assert.assertEquals(stats, testSlackStat);

    }

    @Test
    public void shouldCheckStatByIdThatExist() {
        MessageDto messageDto = new MessageDto();
        SlackStat stats = new SlackStat(new MessageFactory().createWithoutId(messageDto));
        slackStatsRepository.save(stats);


        SlackStat slackSlackStat = slackStatsRepository.findOne(stats.getStatId());
        Assert.assertNotNull(slackSlackStat);
        Assert.assertEquals(slackSlackStat, stats);
    }

    @Test
    public void shouldFindAllStats() {
        MessageDto messageDto = new MessageDto();

        SlackStat slackStat = new SlackStat(new MessageFactory().createWithoutId(messageDto));
        slackStatsRepository.save(slackStat);
        SlackStat slackStat1 = new SlackStat((new MessageFactory().createWithoutId(messageDto)));
        slackStatsRepository.save(slackStat1);
        SlackStat slackStat2 = new SlackStat(new MessageFactory().createWithoutId(messageDto));
        slackStatsRepository.save(slackStat2);

        Iterable<SlackStat> findAllStatsList = slackStatsRepository.findAll();
        List<SlackStat> lisOfAllSlackStats = new ArrayList<>();
        findAllStatsList.forEach(lisOfAllSlackStats::add);

        // first 5 in before
        Assert.assertEquals(lisOfAllSlackStats.size(), 8);
        Assert.assertEquals(lisOfAllSlackStats.get(5), slackStat);
        Assert.assertEquals(lisOfAllSlackStats.get(6), slackStat1);
        Assert.assertEquals(lisOfAllSlackStats.get(7), slackStat2);

    }

    /*@Test
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

    }*/

    @Test
    public void shouldDeleteStat() {
        MessageDto messageDto = new MessageDto();
        SlackStat slackStat = new SlackStat(new MessageFactory().createWithoutId(messageDto));
        slackStatsRepository.save(slackStat);

        slackStatsRepository.delete(slackStat);
        Assert.assertNull(slackStatsRepository.findOne(slackStat.getStatId()));
    }

}
