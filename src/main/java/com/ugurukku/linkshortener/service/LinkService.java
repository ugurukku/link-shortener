package com.ugurukku.linkshortener.service;

import com.ugurukku.linkshortener.model.dto.GeneralResponse;
import com.ugurukku.linkshortener.model.dto.LinkRequest;
import com.ugurukku.linkshortener.model.dto.LinkResponse;

public interface LinkService {

    GeneralResponse<LinkResponse> add(String userEmail, LinkRequest request);

    String redirect(String shortLink);
}
