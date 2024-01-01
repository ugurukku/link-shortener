package com.ugurukku.linkshortener.controller;

import com.ugurukku.linkshortener.model.dto.*;
import com.ugurukku.linkshortener.model.dto.link.ExactLinkResponse;
import com.ugurukku.linkshortener.model.dto.link.LinkPageResponse;
import com.ugurukku.linkshortener.model.dto.link.LinkRequest;
import com.ugurukku.linkshortener.model.dto.link.LinkResponse;
import com.ugurukku.linkshortener.security.MyUserDetails;
import com.ugurukku.linkshortener.service.LinkService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/links")
public record LinkController(LinkService service) {

    @PostMapping
    public GeneralResponse<LinkResponse> add(@RequestBody @Valid LinkRequest request, @AuthenticationPrincipal MyUserDetails userDetails) {
        return service.add(userDetails.getId(), request);
    }

    @GetMapping("/redirect/{shortLink}")
    public GeneralResponse<ExactLinkResponse> redirect(@PathVariable(name = "shortLink") String shortLink) {
        return service.getExactLink(shortLink);
    }

    @GetMapping
    public GeneralResponse<PageResponse<LinkPageResponse>> getByUserId(@AuthenticationPrincipal MyUserDetails userDetails, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int count) {
        return service.getByUserId(userDetails.getId(), PageRequest.of(page, count));
    }

    @DeleteMapping("/{id}")
    public GeneralResponse<Void> deleteById(@AuthenticationPrincipal MyUserDetails userDetails, @PathVariable("id") Integer id) {
        return service.deleteById(userDetails.getId(), id);
    }

}
