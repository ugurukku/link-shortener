package com.ugurukku.linkshortener.service.impl;

import com.ugurukku.linkshortener.model.dto.*;
import com.ugurukku.linkshortener.model.dto.link.LinkAdminResponse;
import com.ugurukku.linkshortener.model.dto.link.LinkChangeStatusRequest;
import com.ugurukku.linkshortener.model.dto.link.LinkPageResponse;
import com.ugurukku.linkshortener.service.AdminService;
import com.ugurukku.linkshortener.service.LinkService;
import com.ugurukku.linkshortener.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminServiceImpl implements AdminService {

    UserService userService;
    LinkService linkService;

    @Override
    public GeneralResponse<PageResponse<UserPageResponse>> getAllUsers(PageRequest pageRequest) {
        PageResponse<UserPageResponse> users = userService.getAll(pageRequest);
        return new GeneralResponse<>(200, "SUCCESS", users);
    }

    @Override
    public GeneralResponse<Void> changeUserStatus(UserChangeStatusRequest request) {
        userService.changeStatusById(request.userId(), request.status().isActive());
        return new GeneralResponse<>(200,"SUCCESS");
    }

    @Override
    public GeneralResponse<Void> removeUser(Integer userId) {
        userService.deleteById(userId);
        return new GeneralResponse<>(200,"SUCCESS");
    }

    @Override
    public GeneralResponse<PageResponse<LinkAdminResponse>> getAllLinks(PageRequest pageRequest) {
        PageResponse<LinkAdminResponse> users = linkService.getAllForAdmin(pageRequest);
        return new GeneralResponse<>(200,"SUCCESS",users);
    }

    @Override
    public GeneralResponse<Void> changeLinkStatus(LinkChangeStatusRequest request) {
        linkService.changeStatusById(request.linkId(),request.status().isActive());
        return new GeneralResponse<>(200,"SUCCESS");
    }

    @Override
    public GeneralResponse<Void> removeLink(Integer linkId) {
        linkService.deleteById(linkId);
        return new GeneralResponse<>(200,"SUCCESS");
    }

}
