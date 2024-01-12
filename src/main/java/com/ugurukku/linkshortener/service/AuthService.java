package com.ugurukku.linkshortener.service;

import com.ugurukku.linkshortener.model.dto.*;
import com.ugurukku.linkshortener.model.dto.auth.*;

public interface AuthService {

    GeneralResponse<AuthResponse> register(RegisterRequest request);

    GeneralResponse<AuthResponse> login(RegisterRequest request);

    GeneralResponse<Void> changePassword(String email, ChangePasswordRequest request);

    GeneralResponse<AuthResponse> verify(VerifyRequest request);

    GeneralResponse<Void> resetPassword(ResetPasswordRequest request);

    GeneralResponse<Void> verifyReset(VerifyResetRequest request);
}
