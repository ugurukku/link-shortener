package com.ugurukku.linkshortener.controller;

import com.ugurukku.linkshortener.model.repository.LinkRepository;
import com.ugurukku.linkshortener.service.redis.RedisService;
import com.ugurukku.linkshortener.util.LinkGeneratorUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public record TestController(
        LinkRepository linkRepository,
        RedisService<String> redisService
) {

    @GetMapping("/all/{test}")
    public ResponseEntity<String> testAll(@PathVariable(name = "test")String test) {
        String ugur = redisService.get("UGUR");
        System.err.println("Consumed data for key UGUR : " + ugur);
        redisService.set("UGUR",test,1);
        return ResponseEntity.ok(null);
    }

    @GetMapping
    public ResponseEntity<String> test(@AuthenticationPrincipal UserDetails userDetails) {
        System.err.println(userDetails.getUsername());
        return ResponseEntity.ok(LinkGeneratorUtil.generate());
    }

}
