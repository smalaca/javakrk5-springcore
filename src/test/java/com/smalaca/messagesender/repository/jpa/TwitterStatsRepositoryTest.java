package com.smalaca.messagesender.repository.jpa;

import com.smalaca.messagesender.domain.TwitterStats;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TwitterStatsRepositoryTest {

    private static final String FROM = "from";
    private static final String TO = "to";
    private static final String SOME_ID = "some id";

    TwitterStats twitterStats = new TwitterStats(SOME_ID, FROM, TO);

    @Autowired
    private TwitterStatsRepository twitterStatsRepository;

    @Before
    public void addStatsToRepoThatDoNotMeetCriteria() {
        TwitterStats twitterStats = new TwitterStats(SOME_ID, FROM, TO);
        TwitterStats twitterStats1 = new TwitterStats(SOME_ID + "1", FROM + "1", TO + "1");
        TwitterStats twitterStats2 = new TwitterStats(SOME_ID + "2", FROM + "2", TO + "2");
        TwitterStats twitterStats3 = new TwitterStats(SOME_ID + "3", FROM + "3", TO + "3");
        TwitterStats twitterStats4 = new TwitterStats(SOME_ID + "4", FROM + "4", TO + "4");

        twitterStatsRepository.save(twitterStats);
        twitterStatsRepository.save(twitterStats1);
        twitterStatsRepository.save(twitterStats2);
        twitterStatsRepository.save(twitterStats3);
        twitterStatsRepository.save(twitterStats4);
    }

    @Test
    public void shouldCheckIfStatExists() {

        assertFalse(twitterStatsRepository.exists(SOME_ID));
        assertNull(twitterStatsRepository.findOne(SOME_ID));
    }

    @Test
    public void shouldConfirmFiveStatsAreAdded() {

        List<TwitterStats> twitterStatsList = toList(twitterStatsRepository.findAll());
        assertEquals(5, twitterStatsList.size());

    }

    @Test
    public void shouldConfirmAdditionalThreeStatsAreAddedAndMatch() {

        TwitterStats persisted1 = twitterStatsRepository.save(
                new TwitterStats(SOME_ID + "5", FROM + "5", TO + "5"));
        TwitterStats persisted2 = twitterStatsRepository.save(
                new TwitterStats(SOME_ID + "6", FROM + "6", TO + "6"));
        TwitterStats persisted3 = twitterStatsRepository.save(
                new TwitterStats(SOME_ID + "7", FROM + "7", TO + "7"));

        Iterator<TwitterStats> twitterStatsList = twitterStatsRepository.findAll().iterator();

        assertTrue(CollectionUtils.contains(twitterStatsList, persisted1));
        assertTrue(CollectionUtils.contains(twitterStatsList, persisted2));
        assertTrue(CollectionUtils.contains(twitterStatsList, persisted3));
    }

    public static List<TwitterStats> toList(final Iterable<TwitterStats> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Test
    public void shouldAddSomeStatsToRepository() {

        twitterStatsRepository.save(twitterStats);

        assertTrue(twitterStatsRepository.exists(twitterStats.getId()));
    }

    @Test
    public void shouldDeleteStatFromRepository() {

        TwitterStats persisted = twitterStatsRepository.save(twitterStats);

        twitterStatsRepository.delete(persisted);
        assertFalse(persisted.getId().isEmpty());
    }

    @Test
    public void shouldUpdateStatsFromRepository() {

        TwitterStats persisted = twitterStatsRepository.save(twitterStats);

        twitterStatsRepository.save(persisted);
        assertTrue(twitterStatsRepository.exists(persisted.getId()));
    }

    @Test
    public void shouldReturnThreeStatsThatMatchComplexQuery() {

        try {
            TwitterStats twitterStats5 = new TwitterStats(SOME_ID + "5", "janusz", "grażyna");
            TimeUnit.MILLISECONDS.sleep(10);
            TwitterStats twitterStats6 = new TwitterStats(SOME_ID + "6", "janusz", "grażyna");
            TimeUnit.MILLISECONDS.sleep(10);
            TwitterStats twitterStats7 = new TwitterStats(SOME_ID + "7", "wiesław", "grażyna");
            TimeUnit.MILLISECONDS.sleep(10);
            TwitterStats twitterStats8 = new TwitterStats(SOME_ID + "8", "janusz", "wiesław");
            TimeUnit.MILLISECONDS.sleep(10);
            TwitterStats twitterStats9 = new TwitterStats(SOME_ID + "9", "janusz", "grażyna");

            twitterStatsRepository.save(twitterStats5);
            twitterStatsRepository.save(twitterStats6);
            twitterStatsRepository.save(twitterStats7);
            twitterStatsRepository.save(twitterStats8);
            twitterStatsRepository.save(twitterStats9);

            List<TwitterStats> twitterStatsList = twitterStatsRepository
                    .findFirst3ByFromContainsOrToContainsOrderByDateDesc("janusz", "grażyna");

            assertEquals(3, twitterStatsList.size());
            assertEquals(twitterStatsList.get(0), twitterStats9);
            assertEquals(twitterStatsList.get(1), twitterStats8);
            assertEquals(twitterStatsList.get(2), twitterStats7);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldReturnTwoStatsThatMatchComplexQuery() {

        try {
            TwitterStats twitterStats5 = new TwitterStats(SOME_ID + "5", "janusz", "grzegorz");
            TimeUnit.MILLISECONDS.sleep(10);
            TwitterStats twitterStats6 = new TwitterStats(SOME_ID + "6", "jan", "zofia");
            TimeUnit.MILLISECONDS.sleep(10);
            TwitterStats twitterStats7 = new TwitterStats(SOME_ID + "7", "filip", "sara");
            TimeUnit.MILLISECONDS.sleep(10);
            TwitterStats twitterStats8 = new TwitterStats(SOME_ID + "8", "krystyna", "grażyna");
            TimeUnit.MILLISECONDS.sleep(10);
            TwitterStats twitterStats9 = new TwitterStats(SOME_ID + "9", "krystyna", "janusz");

            twitterStatsRepository.save(twitterStats5);
            twitterStatsRepository.save(twitterStats6);
            twitterStatsRepository.save(twitterStats7);
            twitterStatsRepository.save(twitterStats8);
            twitterStatsRepository.save(twitterStats9);

            List<TwitterStats> twitterStatsList = twitterStatsRepository
                    .findFirst3ByFromContainsOrToContainsOrderByDateDesc("janusz", "grażyna");

            assertEquals(2, twitterStatsList.size());
            assertEquals(twitterStatsList.get(0), twitterStats8);
            assertEquals(twitterStatsList.get(1), twitterStats5);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldReturnNoStatThatMatchComplexQuery() {

        List<TwitterStats> twitterStatsList = twitterStatsRepository
                .findFirst3ByFromContainsOrToContainsOrderByDateDesc("janusz", "grażyna");

        assertEquals(0, twitterStatsList.size());
    }
}