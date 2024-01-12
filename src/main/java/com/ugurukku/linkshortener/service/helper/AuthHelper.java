package com.ugurukku.linkshortener.service.helper;

import com.ugurukku.linkshortener.exception.BadRequestException;
import com.ugurukku.linkshortener.model.dto.auth.RegisterRequest;
import com.ugurukku.linkshortener.model.dto.auth.ResetPasswordRequest;
import com.ugurukku.linkshortener.model.dto.auth.VerifyRequest;
import com.ugurukku.linkshortener.model.dto.auth.VerifyResetRequest;
import com.ugurukku.linkshortener.service.messaging.RegisterSendEmailDto;
import com.ugurukku.linkshortener.service.messaging.RegisterSendEmailProducer;
import com.ugurukku.linkshortener.service.messaging.reset.ResetSendEmailDto;
import com.ugurukku.linkshortener.service.messaging.reset.ResetSendEmailProducer;
import com.ugurukku.linkshortener.service.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import static com.ugurukku.linkshortener.model.constants.ErrorMessages.OTP_OR_EMAIL_DOES_NOT_MATCH;
import static com.ugurukku.linkshortener.model.constants.OtpConstants.VALIDITY_MINUTES;
import static com.ugurukku.linkshortener.model.constants.RedisKeyPrefixes.VERIFY_REGISTER;
import static com.ugurukku.linkshortener.model.constants.RedisKeyPrefixes.VERIFY_RESET;

@Component
@RequiredArgsConstructor
public class AuthHelper {

    private final RedisService<RegisterRequest> redisRegisterService;
    private final RedisService<ResetPasswordRequest> redisResetService;
    private final OtpHelper otpHelper;
    private final RegisterSendEmailProducer registerSendEmailProducer;
    private final ResetSendEmailProducer resetSendEmailProducer;

    public void prepareForOtpVerify(RegisterRequest request) {
        String otp = otpHelper.generateOtp();
        redisRegisterService.set(VERIFY_REGISTER + otp,request, VALIDITY_MINUTES);
        registerSendEmailProducer.produce(new RegisterSendEmailDto(request.email(),otp, LocaleContextHolder.getLocale().getLanguage()));
    }

    public RegisterRequest verifyRegister(VerifyRequest verifyRequest){
        RegisterRequest registerRequest = redisRegisterService.get(VERIFY_REGISTER + verifyRequest.otp());
        if (registerRequest != null && registerRequest.email().equals(verifyRequest.email())){
            redisRegisterService.delete(VERIFY_RESET + verifyRequest.otp());
            return registerRequest;
        }else {
            throw new BadRequestException(OTP_OR_EMAIL_DOES_NOT_MATCH);
        }
    }

    public void prepareForPasswordReset(ResetPasswordRequest request) {
        String otp = otpHelper.generateOtp();
        redisResetService.set(VERIFY_RESET + otp,request,VALIDITY_MINUTES);
        resetSendEmailProducer.produce(new ResetSendEmailDto(request.email(), otp,LocaleContextHolder.getLocale().getLanguage()));
    }

    public void verifyReset(VerifyResetRequest request) {
        ResetPasswordRequest resetPasswordRequest = redisResetService.get(VERIFY_RESET + request.otp());
        if (resetPasswordRequest != null && resetPasswordRequest.email().equals(request.email())){
            redisRegisterService.delete(VERIFY_RESET + request.otp());
        }else {
            throw new BadRequestException(OTP_OR_EMAIL_DOES_NOT_MATCH);
        }
    }

}
