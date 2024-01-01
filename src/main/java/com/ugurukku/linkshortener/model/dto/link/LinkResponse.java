package com.ugurukku.linkshortener.model.dto.link;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LinkResponse {

    String shortLink;

}
