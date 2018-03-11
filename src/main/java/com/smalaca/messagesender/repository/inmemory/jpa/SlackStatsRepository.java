package com.smalaca.messagesender.repository.inmemory.jpa;

import com.smalaca.messagesender.domain.SlackStat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface SlackStatsRepository extends CrudRepository<SlackStat, Integer> {

    @Query("SELECT ss from SlackStat ss WHERE ss.message.sentFrom LIKE :messageFrom AND ss.message.To LIKE :messageTo")
    List<SlackStat> findFirst3ByMessageFromContainsAndMessageToContainsOrderByMessageToDesc(String messageFrom, String messageTo);


}
