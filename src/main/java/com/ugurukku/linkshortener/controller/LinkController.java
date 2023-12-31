package com.ugurukku.linkshortener.controller;

import com.ugurukku.linkshortener.model.dto.GeneralResponse;
import com.ugurukku.linkshortener.model.dto.LinkRequest;
import com.ugurukku.linkshortener.model.dto.LinkResponse;
import com.ugurukku.linkshortener.security.MyUserDetails;
import com.ugurukku.linkshortener.service.LinkService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/v1/links")
public record LinkController(
        LinkService service
) {

    @PostMapping
    public GeneralResponse<LinkResponse> add(@RequestBody @Valid LinkRequest request, @AuthenticationPrincipal MyUserDetails userDetails) {
        return service.add(userDetails.getId(), request);
    }

    @GetMapping("/redirect/{shortLink}")
    public RedirectView redirect(@PathVariable(name = "shortLink") String shortLink){
        String redirect = service.redirect(shortLink);
        return new RedirectView(redirect);
    }

}
