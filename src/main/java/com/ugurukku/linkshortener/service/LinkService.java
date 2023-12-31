package com.ugurukku.linkshortener.service;

import com.ugurukku.linkshortener.model.dto.*;
import org.springframework.data.domain.PageRequest;

public interface LinkService {

    GeneralResponse<LinkResponse> add(Integer userId, LinkRequest request);

    GeneralResponse<ExactLinkResponse> getExactLink(String shortLink);

    GeneralResponse<PageResponse<LinkPageResponse>> getByUserId(Integer userId, PageRequest request);


}
