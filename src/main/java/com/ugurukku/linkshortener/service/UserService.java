package com.ugurukku.linkshortener.service;

import com.ugurukku.linkshortener.model.dto.PageResponse;
import com.ugurukku.linkshortener.model.dto.RegisterRequest;
import com.ugurukku.linkshortener.model.dto.UserPageResponse;
import com.ugurukku.linkshortener.model.entity.User;
import org.springframework.data.domain.PageRequest;

public interface UserService {

    User getByEmail(String email);

    void update(User user);

    boolean checkByEmail(String email);

    User save(RegisterRequest request);

    PageResponse<UserPageResponse> getAll(PageRequest request);

    void changeStatusById(Integer userId, boolean active);

    void deleteById(Integer userId);
}
