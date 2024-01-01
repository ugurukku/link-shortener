package com.ugurukku.linkshortener.service.impl;

import com.ugurukku.linkshortener.exception.AccessDeniedException;
import com.ugurukku.linkshortener.exception.NotFoundException;
import com.ugurukku.linkshortener.model.dto.*;
import com.ugurukku.linkshortener.model.dto.link.*;
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

import static com.ugurukku.linkshortener.model.constants.ErrorMessages.ACCESS_DENIED_LINK;
import static com.ugurukku.linkshortener.model.constants.ErrorMessages.LINK_NOT_FOUND;
import static com.ugurukku.linkshortener.model.constants.LinkConstants.ROOT_PATH;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class LinkServiceImpl implements LinkService {

    LinkRepository repository;
    LinkMapper mapper;
    LinkHelper linkHelper;

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
        Link link = repository.findByShortLink(shortLink).orElseThrow(() -> new NotFoundException(LINK_NOT_FOUND));
        return new GeneralResponse<>(200,"Success",mapper.mapToExactLinkResponse(link));
    }

    @Override
    public GeneralResponse<PageResponse<LinkPageResponse>> getAllByUserId(Integer userId, PageRequest request) {
        Page<Link> links = repository.findAllByUserId(userId, request);
        return new GeneralResponse<>(200,"Success",mapper.mapToPage(links));
    }

    @Override
    public GeneralResponse<Void> deleteById(Integer userId, Integer id) {
        Link link = getById(id);
        if (link.getUser().getId().equals(userId) || link.getUser().getRole().getRole().equals("ADMIN")){
         repository.deleteById(id);
         return new GeneralResponse<>(200,"Link successfully deleted.");
        }else {
            throw new AccessDeniedException(ACCESS_DENIED_LINK);
        }
    }

    @Override
    public PageResponse<LinkPageResponse> getAll(PageRequest pageRequest) {
        Page<Link> links = repository.findAll(pageRequest);
        return mapper.mapToPage(links);
    }

    @Override
    public PageResponse<LinkAdminResponse> getAllForAdmin(PageRequest pageRequest) {
        Page<Link> links = repository.findAll(pageRequest);
        return mapper.mapToAdminPage(links);
    }

    @Override
    public void changeStatusById(Integer linkId, boolean active) {
        Link link = getById(linkId);
        link.setIsActive(active);
        repository.save(link);
    }

    @Override
    public void deleteById(Integer linkId) {
        repository.deleteById(linkId);
    }

    public Link getById(Integer id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException(LINK_NOT_FOUND));
    }

}
