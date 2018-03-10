package com.smalaca.messagesender.repository.inmemory;

import com.smalaca.messagesender.domain.EmailStats;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MessageStatsRepository extends CrudRepository<EmailStats, String>{

    List<EmailStats> findAllBy();

    List<EmailStats> findAllByDateAfterAndDateBefore(Date after, Date before);

    EmailStats findById(String id);

    List<EmailStats> findAllByFrom(String from);

    List<EmailStats> findAllByTo(String to);

}
