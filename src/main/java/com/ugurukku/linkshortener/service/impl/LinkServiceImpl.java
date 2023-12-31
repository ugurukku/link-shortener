package com.ugurukku.linkshortener.service.impl;

import com.ugurukku.linkshortener.model.dto.GeneralResponse;
import com.ugurukku.linkshortener.model.dto.LinkRequest;
import com.ugurukku.linkshortener.model.dto.LinkResponse;
import com.ugurukku.linkshortener.model.entity.Link;
import com.ugurukku.linkshortener.model.entity.User;
import com.ugurukku.linkshortener.model.mapper.LinkMapper;
import com.ugurukku.linkshortener.model.repository.LinkRepository;
import com.ugurukku.linkshortener.service.LinkService;
import com.ugurukku.linkshortener.service.UserService;
import com.ugurukku.linkshortener.service.helper.LinkHelper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LinkServiceImpl implements LinkService {

    private static final String ROOT_PATH = "http://localhost:8080/api/v1/links/redirect/";
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
    public String redirect(String shortLink) {
        Link link = repository.findByShortLink(shortLink).orElseThrow(() -> new RuntimeException("Short link not found!"));
        return link.getExactLink();
    }
}
