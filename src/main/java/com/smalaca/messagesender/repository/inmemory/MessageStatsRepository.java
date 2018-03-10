package com.smalaca.messagesender.repository.inmemory;

import com.smalaca.messagesender.domain.EmailStat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface MessageStatsRepository extends CrudRepository<EmailStat, String>{

    List<EmailStat> findAll();

    List<EmailStat> findAllByDateAfterAndDateBefore(Timestamp after, Timestamp before);

    EmailStat findById(String id);

    List<EmailStat> findAllByFrom(String from);

    List<EmailStat> findAllByTo(String to);

}
