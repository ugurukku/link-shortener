package com.ugurukku.linkshortener.service;

import com.ugurukku.linkshortener.model.dto.*;
import com.ugurukku.linkshortener.model.dto.link.ExactLinkResponse;
import com.ugurukku.linkshortener.model.dto.link.LinkPageResponse;
import com.ugurukku.linkshortener.model.dto.link.LinkRequest;
import com.ugurukku.linkshortener.model.dto.link.LinkResponse;
import org.springframework.data.domain.PageRequest;

public interface LinkService {

    GeneralResponse<LinkResponse> add(Integer userId, LinkRequest request);

    GeneralResponse<ExactLinkResponse> getExactLink(String shortLink);

    GeneralResponse<PageResponse<LinkPageResponse>> getByUserId(Integer userId, PageRequest request);


    GeneralResponse<Void> deleteById(Integer userId, Integer id);
}
