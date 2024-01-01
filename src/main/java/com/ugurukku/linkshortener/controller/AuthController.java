package com.ugurukku.linkshortener.controller;

import com.ugurukku.linkshortener.model.dto.GeneralResponse;
import com.ugurukku.linkshortener.model.dto.RegisterRequest;
import com.ugurukku.linkshortener.model.dto.RegisterResponse;
import com.ugurukku.linkshortener.model.dto.ResetPasswordRequest;
import com.ugurukku.linkshortener.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/reset")
    public GeneralResponse<Void> reset(@RequestBody @Valid ResetPasswordRequest request){
        return service.reset(request);
    }

}
