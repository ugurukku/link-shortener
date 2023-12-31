package com.ugurukku.linkshortener.model.dto;

import jakarta.validation.constraints.NotBlank;

public record LinkRequest(
        @NotBlank(message = "Exact link should not be empty.")
        String exactLink
) {
}
