package com.ugurukku.linkshortener.service;

import com.ugurukku.linkshortener.model.entity.User;

public interface UserService {

    User getByEmail(String email);

    void update(User user);

    boolean checkByEmail(String email);
}
