package com.ugurukku.linkshortener.model.dto;

import com.ugurukku.linkshortener.model.constants.Status;

public record LinkChangeStatusRequest(
        Integer linkId,
        Status status
) {
}
