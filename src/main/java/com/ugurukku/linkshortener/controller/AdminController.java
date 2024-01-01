package com.ugurukku.linkshortener.controller;

import com.ugurukku.linkshortener.model.dto.*;
import com.ugurukku.linkshortener.model.dto.link.LinkAdminResponse;
import com.ugurukku.linkshortener.model.dto.link.LinkChangeStatusRequest;
import com.ugurukku.linkshortener.model.dto.link.LinkPageResponse;
import com.ugurukku.linkshortener.service.AdminService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public record AdminController(
        AdminService service
) {

    @GetMapping("/users")
    GeneralResponse<PageResponse<UserPageResponse>> getAllUsers(@RequestParam(value = "page",defaultValue = "0")Integer page,
                                                                @RequestParam(value = "count",defaultValue = "10")Integer count){
        return service.getAllUsers(PageRequest.of(page,count));
    }

    @PutMapping("/users")
    GeneralResponse<Void> changeUserStatus(@RequestBody UserChangeStatusRequest request){
        return service.changeUserStatus(request);
    }

    @DeleteMapping("/users/{userId}")
    GeneralResponse<Void> deleteUser(@PathVariable(name = "userId") Integer userId){
        return service.removeUser(userId);
    }

    @GetMapping("/links")
    GeneralResponse<PageResponse<LinkAdminResponse>> getAllLinks(@RequestParam(value = "page",defaultValue = "0")Integer page,
                                                                 @RequestParam(value = "count",defaultValue = "10")Integer count){
        return service.getAllLinks(PageRequest.of(page,count));
    }

    @PutMapping("/links")
    GeneralResponse<Void> changeLinkStatus(@RequestBody LinkChangeStatusRequest request){
        return service.changeLinkStatus(request);
    }

    @DeleteMapping("/links/{linkId}")
    GeneralResponse<Void> deleteLink(@PathVariable(name = "linkId") Integer linkId){
        return service.removeLink(linkId);
    }

}
