package com.ugurukku.linkshortener.service.impl;

import com.ugurukku.linkshortener.model.dto.GeneralResponse;
import com.ugurukku.linkshortener.model.dto.RegisterRequest;
import com.ugurukku.linkshortener.model.dto.RegisterResponse;
import com.ugurukku.linkshortener.model.entity.User;
import com.ugurukku.linkshortener.model.mapper.UserMapper;
import com.ugurukku.linkshortener.model.repository.UserRepository;
import com.ugurukku.linkshortener.security.AccessTokenManager;
import com.ugurukku.linkshortener.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AccessTokenManager accessTokenManager;
    private final UserMapper mapper;

    @Override
    public GeneralResponse<RegisterResponse> register(RegisterRequest request) {
        //TODO CHECK IF USER ALREADY EXIST

        User user = mapper.mapToEntity(request);
        user = userRepository.save(user);

        String accessToken = accessTokenManager.generate(user);
        return new GeneralResponse<>(200,"SUCCESS",RegisterResponse.builder().accessToken(accessToken).build());
    }

    @Override
    public GeneralResponse<RegisterResponse> login(RegisterRequest request) {
        User user = userRepository.findUserByEmail(request.email()).orElseThrow(() -> new RuntimeException("User not found"));
        String accessToken = accessTokenManager.generate(user);
        return new GeneralResponse<>(200,"SUCCESS",RegisterResponse.builder().accessToken(accessToken).build());
    }
}
