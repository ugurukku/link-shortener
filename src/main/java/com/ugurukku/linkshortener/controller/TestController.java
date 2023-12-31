package com.ugurukku.linkshortener.controller;

import com.ugurukku.linkshortener.model.repository.LinkRepository;
import com.ugurukku.linkshortener.util.ShortLinkGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public record TestController(
        LinkRepository linkRepository
) {

    @GetMapping
    public ResponseEntity<String> test(@AuthenticationPrincipal UserDetails userDetails) {
        System.err.println(userDetails.getUsername());
        return ResponseEntity.ok(ShortLinkGenerator.generate());
    }

}
