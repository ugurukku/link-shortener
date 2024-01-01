package com.ugurukku.linkshortener.model.dto;

public record ResetPasswordRequest(String oldPassword, String newPassword) {
}
