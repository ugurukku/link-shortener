package com.ugurukku.linkshortener.service.impl;

import com.ugurukku.linkshortener.model.dto.GeneralResponse;
import com.ugurukku.linkshortener.model.dto.RegisterRequest;
import com.ugurukku.linkshortener.model.dto.RegisterResponse;
import com.ugurukku.linkshortener.model.dto.ResetPasswordRequest;
import com.ugurukku.linkshortener.model.entity.User;
import com.ugurukku.linkshortener.security.AccessTokenManager;
import com.ugurukku.linkshortener.service.AuthService;
import com.ugurukku.linkshortener.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AccessTokenManager accessTokenManager;
    private final PasswordEncoder encoder;
    private final UserService userService;

    @Override
    public GeneralResponse<RegisterResponse> register(RegisterRequest request) {
        User user = userService.save(request);
        String accessToken = accessTokenManager.generate(user);
        return new GeneralResponse<>(200,"SUCCESS",RegisterResponse.builder().accessToken(accessToken).build());
    }

    @Override
    public GeneralResponse<RegisterResponse> login(RegisterRequest request) {
        User user = userService.getByEmail(request.email());
        String accessToken = accessTokenManager.generate(user);
        return new GeneralResponse<>(200,"SUCCESS",RegisterResponse.builder().accessToken(accessToken).build());
    }

    @Override
    public GeneralResponse<Void> resetByEmail(String email,ResetPasswordRequest request) {
        User user = userService.getByEmail(email);
        if (encoder.matches(request.oldPassword(),user.getPassword())){
            user.setPassword(encoder.encode(request.newPassword()));
            userService.update(user);
            return new GeneralResponse<>(200,"Password successfully updated.");
        }else {
            throw new RuntimeException("Old password does not match!");
        }
    }

}