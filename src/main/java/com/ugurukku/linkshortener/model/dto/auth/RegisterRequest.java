package com.ugurukku.linkshortener.model.dto.auth;

public record RegisterRequest(
        String email,
        String password
) {
}
