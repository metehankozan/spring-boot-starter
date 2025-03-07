package dev.metehankozan.contentcalendar.controller;

import dev.metehankozan.contentcalendar.config.ContentCalendarProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final ContentCalendarProperties properties;

    public HomeController(@Qualifier("contentCalendarProperties") ContentCalendarProperties properties) {
        this.properties = properties;
    }

    @GetMapping("/")
    public ContentCalendarProperties home() {
        return properties;
    }
}
