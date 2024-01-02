package com.ugurukku.linkshortener.controller;

import com.ugurukku.linkshortener.model.dto.GeneralResponse;
import com.ugurukku.linkshortener.model.dto.RegisterRequest;
import com.ugurukku.linkshortener.model.dto.AuthResponse;
import com.ugurukku.linkshortener.model.dto.ResetPasswordRequest;
import com.ugurukku.linkshortener.security.MyUserDetails;
import com.ugurukku.linkshortener.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public record AuthController(
        AuthService service
) {

    @PostMapping("/login")
    public GeneralResponse<AuthResponse> login(@RequestBody @Valid RegisterRequest request){
        return service.login(request);
    }

    @PostMapping("/register")
    public GeneralResponse<AuthResponse> register(@RequestBody @Valid RegisterRequest request){
        return service.register(request);
    }

    @PutMapping("/reset")
    public GeneralResponse<Void> reset(@AuthenticationPrincipal MyUserDetails userDetails, @RequestBody @Valid ResetPasswordRequest request){
        return service.resetByEmail(userDetails.getUsername(),request);
    }

}
