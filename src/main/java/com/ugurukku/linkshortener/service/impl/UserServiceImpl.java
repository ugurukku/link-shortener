package com.ugurukku.linkshortener.service.impl;

import com.ugurukku.linkshortener.exception.NotFoundException;
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
        return repository.findUserByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public void update(User user) {
        repository.save(user);
    }

    @Override
    public boolean checkByEmail(String email) {
        return repository.existsByEmail(email);
    }

}
