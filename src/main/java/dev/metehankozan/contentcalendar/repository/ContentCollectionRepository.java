package dev.metehankozan.contentcalendar.repository;

import dev.metehankozan.contentcalendar.model.Content;
import dev.metehankozan.contentcalendar.model.Status;
import dev.metehankozan.contentcalendar.model.Type;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {

    private final List<Content> contents = new ArrayList<>();

    public List<Content> findAll() {
        return contents;
    }

    public Optional<Content> findById(Integer id) {
        return contents.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public void save(Content content) {
        contents.removeIf(c -> c.getId().equals(content.getId()));
        contents.add(content);
    }

    public void deleteById(Integer id) {
        contents.removeIf(c -> c.getId().equals(id));
    }

    public boolean existsById(Integer id) {
        return contents.stream().filter(c -> c.getId().equals(id)).count() == 1;
    }

    @PostConstruct
    private void init() {
        Content c = new Content(1,
                "my blog post",
                "blog post",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                "");
        contents.add(c);
    }


}
