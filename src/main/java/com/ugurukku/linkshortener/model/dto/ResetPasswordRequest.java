package com.ugurukku.linkshortener.model.dto;

public record ResetPasswordRequest(String email, String oldPassword, String newPassword) {
}
