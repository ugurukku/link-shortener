package com.ugurukku.linkshortener.model.dto.link;

import jakarta.validation.constraints.NotBlank;

public record LinkRequest(
        @NotBlank(message = "Exact link should not be empty.")
        String exactLink
) {
}
