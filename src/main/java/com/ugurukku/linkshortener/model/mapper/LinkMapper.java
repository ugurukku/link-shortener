package com.ugurukku.linkshortener.model.mapper;

import com.ugurukku.linkshortener.model.dto.LinkRequest;
import com.ugurukku.linkshortener.model.entity.Link;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class LinkMapper {

    public Link mapToEntity(LinkRequest request){
        return Link.builder()
                .exactLink(request.exactLink())
                .isActive(true)
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }

}
