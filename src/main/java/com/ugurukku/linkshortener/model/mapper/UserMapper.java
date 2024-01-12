package com.ugurukku.linkshortener.model.mapper;

import com.ugurukku.linkshortener.model.dto.auth.RegisterRequest;
import com.ugurukku.linkshortener.model.dto.UserPageResponse;
import com.ugurukku.linkshortener.model.entity.Role;
import com.ugurukku.linkshortener.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder encoder;

    public User mapToEntity(RegisterRequest request) {
        return User.builder()
                .email(request.email())
                .isActive(true)
                .role(Role.builder().id(1).build())
                .password(encoder.encode(request.password()))
                .build();
    }

    public List<UserPageResponse> mapToPageResponse(List<User> users) {
        return users.stream()
                .map(user -> new UserPageResponse(
                        user.getId(),
                        user.getEmail(),
                        user.getRole().getRole(),
                        user.getCreatedAt(),
                        user.getIsActive()))
                .collect(Collectors.toList());
    }
}
