package com.ugurukku.linkshortener.model.dto.auth;

public record ChangePasswordRequest(String oldPassword, String newPassword) {
}
