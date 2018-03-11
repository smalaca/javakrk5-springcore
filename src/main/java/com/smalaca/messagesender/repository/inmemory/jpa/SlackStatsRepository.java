package com.smalaca.messagesender.repository.inmemory.jpa;

import com.smalaca.messagesender.domain.SlackStat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SlackStatsRepository extends CrudRepository<SlackStat, Integer> {

    List<SlackStat> findFirst3ByMessageFromContainsAndMessageToContainsOrderByMessageToDesc(String messageFrom, String messageTo);


    @Query("SELECT messagefrom from SlackStat WHERE messageId - :id")
    String findFromByMessageId(@Param("id") String messageId);

}
