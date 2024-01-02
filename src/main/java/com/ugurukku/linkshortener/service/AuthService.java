package com.ugurukku.linkshortener.service;

import com.ugurukku.linkshortener.model.dto.*;

public interface AuthService {

    GeneralResponse<AuthResponse> register(RegisterRequest request);
    GeneralResponse<AuthResponse> login(RegisterRequest request);
    GeneralResponse<Void> resetByEmail(String email,ResetPasswordRequest request);
    GeneralResponse<AuthResponse> verify(VerifyRequest request);

}
