package com.ugurukku.linkshortener.service.impl;

import com.ugurukku.linkshortener.model.dto.*;
import com.ugurukku.linkshortener.model.entity.Link;
import com.ugurukku.linkshortener.model.entity.User;
import com.ugurukku.linkshortener.model.mapper.LinkMapper;
import com.ugurukku.linkshortener.model.repository.LinkRepository;
import com.ugurukku.linkshortener.service.LinkService;
import com.ugurukku.linkshortener.service.helper.LinkHelper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static com.ugurukku.linkshortener.model.constants.LinkConstants.ROOT_PATH;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LinkServiceImpl implements LinkService {

    final LinkRepository repository;
    final LinkMapper mapper;
    final LinkHelper linkHelper;

    @Override
    public GeneralResponse<LinkResponse> add(Integer userId, LinkRequest request) {
        Link link = mapper.mapToEntity(request);
        link.setUser(User.builder().id(userId).build());
        link.setShortLink(linkHelper.generateShortLink());
        repository.save(link);

        String shortLink = ROOT_PATH + link.getShortLink();
        return new GeneralResponse<>(200,"Success",LinkResponse.builder().shortLink(shortLink).build());
    }

    @Override
    public GeneralResponse<ExactLinkResponse> getExactLink(String shortLink) {
        Link link = repository.findByShortLink(shortLink).orElseThrow(() -> new RuntimeException("Short link not found!"));
        return new GeneralResponse<>(200,"Success",mapper.mapToExactLinkResponse(link));
    }

    @Override
    public GeneralResponse<PageResponse<LinkPageResponse>> getByUserId(Integer userId, PageRequest request) {
        Page<Link> links = repository.findAllByUserId(userId, request);
        return new GeneralResponse<>(200,"Success",mapper.mapToPage(links));
    }

}
