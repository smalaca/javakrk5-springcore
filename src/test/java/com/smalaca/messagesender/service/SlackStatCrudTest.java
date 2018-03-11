/*
package com.smalaca.messagesender.service;

import com.smalaca.messagesender.domain.SlackStat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SlackStatCrudTest {

    @Autowired
    SlackStatsCrud slackStatsCrud;

    @Test
    public void shouldAddMessageToRepository(){
        SlackStat slackStat = new SlackStat("andrew", "brian", "45");
        Response response = slackStatsCrud.createStatistic(slackStat);

        Assert.assertTrue(response.isSuccess());
        Assert.assertEquals(response.getMessage(), "Slack statistic added");
    }

    @Test
    public void shouldNotAddMessageToRepositoryWhenNoArgumentsPassed(){
        SlackStat slackStat = new SlackStat("", "", "");
        Response response = slackStatsCrud.createStatistic(slackStat);

        Assert.assertFalse(response.isSuccess());
        Assert.assertEquals(response.getMessage(), "Cannot add statistic with empty fields");
    }

    @Test
    public void shouldDeleteMessageFromRepository(){
        SlackStat slackStat = new SlackStat("andrew", "brian", "45");
        Response responseAdd = slackStatsCrud.createStatistic(slackStat);
        Response responseDelete = slackStatsCrud.delete(slackStat.getId());

        Assert.assertTrue(responseAdd.isSuccess());
        Assert.assertTrue(responseDelete.isSuccess());
        Assert.assertEquals(responseDelete.getMessage(), "Statistic delete");
    }

    @Test
    public void shouldNotDeleteMessageFromRepository(){
        SlackStat slackStat = new SlackStat("andrew", "brian", "45");
        Response responseAdd = slackStatsCrud.createStatistic(slackStat);
        Response responseDelete = slackStatsCrud.delete(slackStat.getId());

        Assert.assertTrue(responseAdd.isSuccess());
        Assert.assertFalse(responseDelete.isSuccess());
        Assert.assertEquals(responseDelete.getMessage(), "Statistic not exist");
    }

    @Test
    public void shouldFindOneStatisticById(){
        SlackStat slackStat = new SlackStat("andrew", "brian", "45");
        Response response = slackStatsCrud.createStatistic(slackStat);
        SlackStat testSlackStat = slackStatsCrud.findById(slackStat.getId());

        Assert.assertEquals(slackStat, testSlackStat);
        Assert.assertTrue(response.isSuccess());
    }

    @Test
    public void shouldNotFindOneWhenNonExistingIdPassed(){
        SlackStat slackStat = new SlackStat("andrew", "brian", "45");
        Response response = slackStatsCrud.createStatistic(slackStat);
        SlackStat testSlackStat = slackStatsCrud.findById(slackStat.getId()+8);

        Assert.assertTrue(testSlackStat == null);
        Assert.assertNotEquals(slackStat, testSlackStat);
        Assert.assertTrue(response.isSuccess());
    }
}
*/
