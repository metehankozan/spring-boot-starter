package dev.metehankozan.contentcalendar.repository;

import dev.metehankozan.contentcalendar.model.Content;
import dev.metehankozan.contentcalendar.model.Status;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContentRepository extends CrudRepository<Content, Integer> {
    List<Content> findByTitleContains(String keyword);

    @Query("SELECT * from Content where status = :status")
    List<Content> listByStatus(@Param("status") Status status);
}
