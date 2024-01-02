package com.ugurukku.linkshortener.service.helper;

import com.ugurukku.linkshortener.exception.BadRequestException;
import com.ugurukku.linkshortener.model.constants.ErrorMessages;
import com.ugurukku.linkshortener.model.dto.RegisterRequest;
import com.ugurukku.linkshortener.model.dto.VerifyRequest;
import com.ugurukku.linkshortener.service.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthHelper {

    private final RedisService<RegisterRequest> redisService;
    private final OtpHelper otpHelper;
    private static final long VALIDITY_MINUTES = 5;

    public void prepareForOtpVerify(RegisterRequest request) {
        String otp = otpHelper.generateOtp();
        redisService.set(otp,request,VALIDITY_MINUTES);
    }

    public RegisterRequest verify(VerifyRequest verifyRequest){
        RegisterRequest registerRequest = redisService.get(verifyRequest.otp());
        if (registerRequest != null && registerRequest.email().equals(verifyRequest.email())){
            redisService.delete(verifyRequest.otp());
            return registerRequest;
        }else {
            throw new BadRequestException(ErrorMessages.OTP_AND_EMAIL_DOES_NOT_MATCH);
        }
    }

}
