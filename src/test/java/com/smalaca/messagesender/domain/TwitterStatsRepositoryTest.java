package com.smalaca.messagesender.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TwitterStatsRepositoryTest {

    private static final String FROM = "from";
    private static final String TO = "to";
    private static final String SOME_ID = "13";

    TwitterStats twitterStats = new TwitterStats(SOME_ID, FROM, TO);
    @Autowired
    private TwitterStatsRepository twitterStatsRepository;

    @Test
    public void shouldStatExists() {

        assertFalse(twitterStatsRepository.exists(SOME_ID));
    }

    @Test
    public void shouldAddSomeStatsToRepository() {
        twitterStatsRepository.save(twitterStats);

        assertTrue(twitterStatsRepository.exists(twitterStats.getId()));
    }

    @Test
    public void shouldDeleteStatFromRepository() {
        TwitterStats persisted = twitterStatsRepository.save(twitterStats);

        twitterStatsRepository.delete(persisted.getId());
    }
//
//    @Test
//    public void shouldUpdateStatsFromRepository(){
//
//        twitterStatsRepository.save(twitterStats);
//
//        if(twitterStatsRepository.exists(twitterStats.getId())){
//            twitterStatsRepository.
//        }
//    }

}
