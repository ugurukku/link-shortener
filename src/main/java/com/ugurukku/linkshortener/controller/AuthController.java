package com.ugurukku.linkshortener.controller;

import com.ugurukku.linkshortener.model.dto.*;
import com.ugurukku.linkshortener.model.dto.auth.*;
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
    public GeneralResponse<AuthResponse> login(@RequestBody @Valid RegisterRequest request) {
        return service.login(request);
    }

    @PostMapping("/register")
    public GeneralResponse<AuthResponse> register(@RequestBody @Valid RegisterRequest request) {
        return service.register(request);
    }

    @PostMapping("/verify")
    public GeneralResponse<AuthResponse> verify(@RequestBody VerifyRequest request) {
        return service.verify(request);
    }

    @PutMapping("/change_password")
    public GeneralResponse<Void> changePassword(@AuthenticationPrincipal MyUserDetails userDetails, @RequestBody @Valid ChangePasswordRequest request) {
        return service.changePassword(userDetails.getUsername(), request);
    }

    @PutMapping("/reset_password")
    public GeneralResponse<Void> resetPassword(@RequestBody ResetPasswordRequest request) {
        return service.resetPassword(request);
    }

    @PutMapping("/verify_reset")
    public GeneralResponse<Void> verifyReset(@RequestBody VerifyResetRequest request){
        return service.verifyReset(request);
    }

}
