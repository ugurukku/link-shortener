package com.ugurukku.linkshortener.model.dto.auth;

public record VerifyResetRequest(
        String email,
        String otp,
        String newPassword
) {
}
