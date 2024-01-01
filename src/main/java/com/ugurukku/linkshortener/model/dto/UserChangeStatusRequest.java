package com.ugurukku.linkshortener.model.dto;

import com.ugurukku.linkshortener.model.constants.Status;

public record UserChangeStatusRequest(
        Integer userId,
        Status status
) {
}
