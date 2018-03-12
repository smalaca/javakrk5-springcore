package com.smalaca.messagesender.repository.inmemory;

import com.smalaca.messagesender.domain.EmailStat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface MessageStatsRepository extends CrudRepository<EmailStat, String>{

    List<EmailStat> findAll();

    List<EmailStat> findAllByDateAfterAndDateBefore(Timestamp after, Timestamp before);

    EmailStat findById(String id);

    List<EmailStat> findTop3ByDateAfterAndDateBeforeAndFromContainsOrderByFromAsc(Timestamp after, Timestamp before, String fromPart);

    @Query("SELECT id FROM EmailStat WHERE sentfrom = :name")
    Optional<String> findIdByName(@Param("name") String name);

}
