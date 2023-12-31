package com.ugurukku.linkshortener.controller;

import com.ugurukku.linkshortener.model.dto.GeneralResponse;
import com.ugurukku.linkshortener.model.dto.LinkRequest;
import com.ugurukku.linkshortener.model.dto.LinkResponse;
import com.ugurukku.linkshortener.model.entity.User;
import com.ugurukku.linkshortener.service.LinkService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/v1/links")
public record LinkController(
        LinkService service
) {

    @PostMapping
    public GeneralResponse<LinkResponse> add(@RequestBody @Valid LinkRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        return service.add(userDetails.getUsername(), request);
    }

    @GetMapping("/redirect/{shortLink}")
    public RedirectView redirect(@PathVariable(name = "shortLink") String shortLink){
        String redirect = service.redirect(shortLink);
        return new RedirectView(redirect);
    }

}