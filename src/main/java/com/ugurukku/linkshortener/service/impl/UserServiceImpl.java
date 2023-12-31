package com.ugurukku.linkshortener.service.impl;

import com.ugurukku.linkshortener.model.entity.User;
import com.ugurukku.linkshortener.model.repository.UserRepository;
import com.ugurukku.linkshortener.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User getByEmail(String email) {
        return repository.findUserByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Integer getUserIdByEmail(String email){
        return getByEmail(email).getId();
    }

}
