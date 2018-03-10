package com.smalaca.messagesender.repository.inmemory;

import com.smalaca.messagesender.domain.EmailStat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MessageStatsRepository extends CrudRepository<EmailStat, String>{

    List<EmailStat> findAllBy();

    List<EmailStat> findAllByDateAfterAndDateBefore(Date after, Date before);

    EmailStat findById(String id);

    List<EmailStat> findAllByFrom(String from);

    List<EmailStat> findAllByTo(String to);

}
