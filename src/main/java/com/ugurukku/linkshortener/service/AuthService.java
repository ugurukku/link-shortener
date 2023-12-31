package com.ugurukku.linkshortener.service;

import com.ugurukku.linkshortener.model.dto.GeneralResponse;
import com.ugurukku.linkshortener.model.dto.RegisterRequest;
import com.ugurukku.linkshortener.model.dto.RegisterResponse;

public interface AuthService {

    GeneralResponse<RegisterResponse> register(RegisterRequest request);

    GeneralResponse<RegisterResponse> login(RegisterRequest request);
}
