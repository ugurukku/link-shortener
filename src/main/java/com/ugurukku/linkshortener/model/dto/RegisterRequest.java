package com.ugurukku.linkshortener.model.dto;

public record RegisterRequest(
        String email,
        String password
) {
}
