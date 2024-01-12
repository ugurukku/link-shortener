package com.ugurukku.linkshortener.model.dto.auth;

public record VerifyRequest(
        String email,
        String otp
) {
}
