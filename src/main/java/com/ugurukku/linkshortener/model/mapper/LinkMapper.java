package com.ugurukku.linkshortener.model.mapper;

import com.ugurukku.linkshortener.model.dto.*;
import com.ugurukku.linkshortener.model.dto.link.ExactLinkResponse;
import com.ugurukku.linkshortener.model.dto.link.LinkAdminResponse;
import com.ugurukku.linkshortener.model.dto.link.LinkPageResponse;
import com.ugurukku.linkshortener.model.dto.link.LinkRequest;
import com.ugurukku.linkshortener.model.entity.Link;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.ugurukku.linkshortener.model.constants.LinkConstants.ROOT_PATH;

@Component
public class LinkMapper {

    public Link mapToEntity(LinkRequest request) {
        return Link.builder()
                .exactLink(request.exactLink())
                .isActive(true)
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }

    public ExactLinkResponse mapToExactLinkResponse(Link link) {
        return ExactLinkResponse.builder()
                .exactLink(link.getExactLink())
                .build();
    }

    public PageResponse<LinkPageResponse> mapToPage(Page<Link> links) {
        List<LinkPageResponse> linkPageResponses = mapToPageResponse(links.getContent());
        return PageResponse.<LinkPageResponse>builder()
                .hasNext(links.hasNext())
                .totalPages(links.getTotalPages())
                .elements(linkPageResponses)
                .build();
    }

    public List<LinkPageResponse> mapToPageResponse(List<Link> links) {
        return links.stream()
                .map(link ->
                        LinkPageResponse.builder()
                                .id(link.getId())
                                .shortLink(ROOT_PATH + link.getShortLink())
                                .createdAt(link.getCreatedAt())
                                .isActive(link.getIsActive())
                                .exactLink(link.getExactLink())
                                .build()
                )
                .collect(Collectors.toList());
    }

    public PageResponse<LinkAdminResponse> mapToAdminPage(Page<Link> links) {
        return PageResponse.<LinkAdminResponse>builder()
                .totalPages(links.getTotalPages())
                .hasNext(links.hasNext())
                .elements(mapToAdminResponse(links.getContent()))
                .build();
    }

    public List<LinkAdminResponse> mapToAdminResponse(List<Link> links) {
        return links.stream()
                .map(link ->
                        LinkAdminResponse.builder()
                                .id(link.getId())
                                .shortLink(ROOT_PATH + link.getShortLink())
                                .userId(link.getUser().getId())
                                .createdAt(link.getCreatedAt())
                                .isActive(link.getIsActive())
                                .exactLink(link.getExactLink())
                                .build()
                )
                .collect(Collectors.toList());
    }
}
