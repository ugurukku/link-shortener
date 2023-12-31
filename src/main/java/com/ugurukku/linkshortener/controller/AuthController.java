package com.ugurukku.linkshortener.controller;

import com.ugurukku.linkshortener.model.dto.GeneralResponse;
import com.ugurukku.linkshortener.model.dto.RegisterRequest;
import com.ugurukku.linkshortener.model.dto.RegisterResponse;
import com.ugurukku.linkshortener.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public record AuthController(
        AuthService service
) {

    @PostMapping("/login")
    public GeneralResponse<RegisterResponse> login(@RequestBody @Valid RegisterRequest request){
        return service.login(request);
    }

    @PostMapping("/register")
    public GeneralResponse<RegisterResponse> register(@RequestBody @Valid RegisterRequest request){
        return service.register(request);
    }

}
