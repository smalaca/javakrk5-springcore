package com.smalaca.messagesender.domain;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TwitterStatsRepositoryTest {

    private static final String FROM = "from";
    private static final String TO = "to";
    private static final String SOME_ID = "13";

    @Autowired
    TwitterStatsRepository twitterStatsRepository;

    @Test
    public void shouldCheckIfStatExists() {

        assertFalse(twitterStatsRepository.exists(SOME_ID));
        assertNull(twitterStatsRepository.findOne(SOME_ID));
    }

    @Test
    public void shouldConfirmThreeStatsAreAdded() {

        twitterStatsRepository.save(new TwitterStats(SOME_ID, FROM, TO));
        twitterStatsRepository.save(new TwitterStats("14", FROM + " 1", TO + " 1"));
        twitterStatsRepository.save(new TwitterStats("15", FROM + " 2", TO + " 2"));

        List<TwitterStats> twitterStatsList = toList(twitterStatsRepository.findAll());
        assertEquals(3, twitterStatsList.size());

    }

    @Test
    public void shouldConfirmThreeStatsAreAddedAndMatch() {

        TwitterStats persisted1 = twitterStatsRepository.save(new TwitterStats(SOME_ID, FROM, TO));
        TwitterStats persisted2 = twitterStatsRepository.save(new TwitterStats("14", FROM + " 1", TO + " 1"));
        TwitterStats persisted3 = twitterStatsRepository.save(new TwitterStats("15", FROM + " 2", TO + " 2"));

        Iterator<TwitterStats> twitterStatsList = twitterStatsRepository.findAll().iterator();

        assertTrue(CollectionUtils.contains(twitterStatsList, persisted1));
        assertTrue(CollectionUtils.contains(twitterStatsList, persisted2));
        assertTrue(CollectionUtils.contains(twitterStatsList, persisted3));
    }

    public static List<TwitterStats> toList(final Iterable<TwitterStats> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }
}