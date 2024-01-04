package com.ugurukku.linkshortener.service.impl;

import com.ugurukku.linkshortener.exception.BadRequestException;
import com.ugurukku.linkshortener.model.dto.*;
import com.ugurukku.linkshortener.model.entity.User;
import com.ugurukku.linkshortener.security.AccessTokenManager;
import com.ugurukku.linkshortener.service.AuthService;
import com.ugurukku.linkshortener.service.UserService;
import com.ugurukku.linkshortener.service.helper.AuthHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.ugurukku.linkshortener.model.constants.ErrorMessages.USER_ALREADY_EXISTS;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AccessTokenManager accessTokenManager;
    private final PasswordEncoder encoder;
    private final UserService userService;
    private final AuthHelper helper;

    @Override
    public GeneralResponse<AuthResponse> register(RegisterRequest request) {
        checkIfUserExists(request.email());
        helper.prepareForOtpVerify(request);
        return new GeneralResponse<>(200, "SUCCESS");
    }

    @Override
    public GeneralResponse<AuthResponse> login(RegisterRequest request) {
        User user = userService.getByEmail(request.email());
        String accessToken = accessTokenManager.generate(user);
        return new GeneralResponse<>(200, "SUCCESS", new AuthResponse(accessToken));
    }

    @Override
    public GeneralResponse<Void> resetByEmail(String email, ResetPasswordRequest request) {
        User user = userService.getByEmail(email);
        if (encoder.matches(request.oldPassword(), user.getPassword())) {
            user.setPassword(encoder.encode(request.newPassword()));
            userService.update(user);
            return new GeneralResponse<>(200, "Password successfully updated.");
        } else {
            throw new RuntimeException("Old password does not match!");
        }
    }

    @Override
    public GeneralResponse<AuthResponse> verify(VerifyRequest verifyRequest) {
        RegisterRequest registerRequest = helper.verify(verifyRequest);
        User user = userService.save(registerRequest);
        String accessToken = accessTokenManager.generate(user);
        return new GeneralResponse<>(201, "SUCCESS", new AuthResponse(accessToken));
    }

    private void checkIfUserExists(String email) {
        boolean checkByEmail = userService.checkByEmail(email);
        if (checkByEmail) {
            throw new BadRequestException(USER_ALREADY_EXISTS);
        }
    }

}