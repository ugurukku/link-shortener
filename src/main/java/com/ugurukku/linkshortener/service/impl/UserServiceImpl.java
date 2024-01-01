package com.ugurukku.linkshortener.service.impl;

import com.ugurukku.linkshortener.exception.BadRequestException;
import com.ugurukku.linkshortener.exception.NotFoundException;
import com.ugurukku.linkshortener.model.dto.PageResponse;
import com.ugurukku.linkshortener.model.dto.RegisterRequest;
import com.ugurukku.linkshortener.model.dto.UserPageResponse;
import com.ugurukku.linkshortener.model.entity.User;
import com.ugurukku.linkshortener.model.mapper.UserMapper;
import com.ugurukku.linkshortener.model.repository.UserRepository;
import com.ugurukku.linkshortener.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static com.ugurukku.linkshortener.model.constants.ErrorMessages.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository repository;
    UserMapper mapper;

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

    @Override
    public User save(RegisterRequest request) {
        checkIfUserExists(request.email());
        User user = mapper.mapToEntity(request);
        return repository.save(user);
    }

    @Override
    public PageResponse<UserPageResponse> getAll(PageRequest request) {
        Page<User> users = repository.findAll(request);
        return PageResponse.<UserPageResponse>builder()
                .totalPages(users.getTotalPages())
                .hasNext(users.hasNext())
                .elements(mapper.mapToPageResponse(users.getContent()))
                .build();
    }

    @Override
    public void changeStatusById(Integer userId, boolean active) {
        User user = findById(userId);
        user.setIsActive(active);
        repository.save(user);
    }

    @Override
    public void deleteById(Integer userId) {
        repository.deleteById(userId);
    }

    private User findById(Integer userId) {
        return repository.findById(userId).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    }

    private void checkIfUserExists(String email) {
        boolean checkByEmail = checkByEmail(email);
        if (checkByEmail) {
            throw new BadRequestException(USER_ALREADY_EXISTS);
        }
    }

}
