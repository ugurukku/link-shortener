package com.ugurukku.linkshortener.model.constants;

import lombok.Getter;

public enum Status {

    ACTIVE(true),
    INACTIVE(false);

    @Getter
    private final boolean isActive;

    Status(boolean isActive) {
        this.isActive = isActive;
    }

}
