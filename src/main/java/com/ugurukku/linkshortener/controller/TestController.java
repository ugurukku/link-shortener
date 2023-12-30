package com.ugurukku.linkshortener.controller;

import com.ugurukku.linkshortener.model.entity.Link;
import com.ugurukku.linkshortener.model.repository.LinkRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/test")
public record TestController(
        LinkRepository linkRepository
) {


    @GetMapping("/add")
    public ResponseEntity<Void> add() {

        Link link = Link.builder()
                .shortLink("short")
                .exactLink("exact")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .userId(1)
                .isActive(true)
                .build();

        linkRepository.insertLink(link);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public List<Link> getAll() {
        return linkRepository.findAll();
    }

}
