package dev.metehankozan.contentcalendar.controller;

import dev.metehankozan.contentcalendar.model.Content;
import dev.metehankozan.contentcalendar.model.Status;
import dev.metehankozan.contentcalendar.repository.ContentCollectionRepository;
import dev.metehankozan.contentcalendar.repository.ContentJdbcTemplateRepository;
import dev.metehankozan.contentcalendar.repository.ContentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Stack;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    private final ContentCollectionRepository repository;
    private final ContentJdbcTemplateRepository jdbcTemplateRepository;
    private final ContentRepository contentRepository;

    public ContentController(ContentCollectionRepository repository, ContentJdbcTemplateRepository jdbcTemplateRepository, ContentRepository contentRepository) {
        this.repository = repository;
        this.jdbcTemplateRepository = jdbcTemplateRepository;
        this.contentRepository = contentRepository;
    }

    @GetMapping("")
    public Iterable<Content> findAll() {
        return contentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id) {
        return contentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody Content content) {
        contentRepository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Content content, @PathVariable Integer id) {
        if(!contentRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
        }
        contentRepository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        if(!contentRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
        }
        contentRepository.deleteById(id);
    }

    @GetMapping("/filter/{keyword}")
    public List<Content> findByTitle(@PathVariable String keyword) {
        return contentRepository.findByTitleContains(keyword);
    }

    @GetMapping("/filter/status/{status}")
    public List<Content> findByStatus(@PathVariable Status status) {
        return contentRepository.listByStatus(status);
    }

}
