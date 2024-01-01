package com.ugurukku.linkshortener.service;

import com.ugurukku.linkshortener.model.dto.*;
import com.ugurukku.linkshortener.model.dto.link.LinkAdminResponse;
import com.ugurukku.linkshortener.model.dto.link.LinkChangeStatusRequest;
import com.ugurukku.linkshortener.model.dto.link.LinkPageResponse;
import org.springframework.data.domain.PageRequest;

public interface AdminService {

    GeneralResponse<PageResponse<UserPageResponse>> getAllUsers(PageRequest pageRequest);

    GeneralResponse<Void> changeUserStatus(UserChangeStatusRequest request);

    GeneralResponse<Void> removeUser(Integer userId);

    GeneralResponse<PageResponse<LinkAdminResponse>> getAllLinks(PageRequest pageRequest);

    GeneralResponse<Void> changeLinkStatus(LinkChangeStatusRequest request);

    GeneralResponse<Void> removeLink(Integer linkId);

}
