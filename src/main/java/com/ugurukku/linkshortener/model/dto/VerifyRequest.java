package com.ugurukku.linkshortener.model.dto;

public record VerifyRequest(
        String email,
        String otp
) {
}
