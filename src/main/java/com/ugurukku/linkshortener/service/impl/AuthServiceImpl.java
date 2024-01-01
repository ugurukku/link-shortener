package com.ugurukku.linkshortener.service.impl;

import com.ugurukku.linkshortener.exception.BadRequestException;
import com.ugurukku.linkshortener.model.dto.GeneralResponse;
import com.ugurukku.linkshortener.model.dto.RegisterRequest;
import com.ugurukku.linkshortener.model.dto.RegisterResponse;
import com.ugurukku.linkshortener.model.dto.ResetPasswordRequest;
import com.ugurukku.linkshortener.model.entity.User;
import com.ugurukku.linkshortener.model.mapper.UserMapper;
import com.ugurukku.linkshortener.model.repository.UserRepository;
import com.ugurukku.linkshortener.security.AccessTokenManager;
import com.ugurukku.linkshortener.service.AuthService;
import com.ugurukku.linkshortener.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.ugurukku.linkshortener.model.constants.ErrorMessages.USER_ALREADY_EXISTS;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AccessTokenManager accessTokenManager;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;
    private final UserService userService;

    @Override
    public GeneralResponse<RegisterResponse> register(RegisterRequest request) {


        checkIfUserExists(request.email());

        User user = mapper.mapToEntity(request);
        user = userRepository.save(user);

        String accessToken = accessTokenManager.generate(user);
        return new GeneralResponse<>(200,"SUCCESS",RegisterResponse.builder().accessToken(accessToken).build());
    }

    private void checkIfUserExists(String email) {
        boolean checkByEmail = userService.checkByEmail(email);
        if (checkByEmail){
            throw new BadRequestException(USER_ALREADY_EXISTS);
        }

    }

    @Override
    public GeneralResponse<RegisterResponse> login(RegisterRequest request) {
        User user = userService.getByEmail(request.email());
        String accessToken = accessTokenManager.generate(user);
        return new GeneralResponse<>(200,"SUCCESS",RegisterResponse.builder().accessToken(accessToken).build());
    }

    @Override
    public GeneralResponse<Void> reset(ResetPasswordRequest request) {
        User user = userService.getByEmail(request.email());
        if (encoder.matches(request.oldPassword(),user.getPassword())){
            user.setPassword(encoder.encode(request.newPassword()));
            userService.update(user);
            return new GeneralResponse<>(200,"Password successfully updated.");
        }else {
            throw new RuntimeException("Old password does not match!");
        }
    }
}
