package com.ugurukku.linkshortener.service.helper;

import com.ugurukku.linkshortener.exception.BadRequestException;
import com.ugurukku.linkshortener.model.constants.ErrorMessages;
import com.ugurukku.linkshortener.model.constants.OtpConstants;
import com.ugurukku.linkshortener.model.dto.RegisterRequest;
import com.ugurukku.linkshortener.model.dto.VerifyRequest;
import com.ugurukku.linkshortener.service.messaging.SendEmailDto;
import com.ugurukku.linkshortener.service.messaging.SendEmailProducer;
import com.ugurukku.linkshortener.service.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import static com.ugurukku.linkshortener.model.constants.ErrorMessages.OTP_AND_EMAIL_DOES_NOT_MATCH;
import static com.ugurukku.linkshortener.model.constants.OtpConstants.VALIDITY_MINUTES;

@Component
@RequiredArgsConstructor
public class AuthHelper {

    private final RedisService<RegisterRequest> redisService;
    private final OtpHelper otpHelper;
    private final SendEmailProducer emailProducer;

    public void prepareForOtpVerify(RegisterRequest request) {
        String otp = otpHelper.generateOtp();
        redisService.set(otp,request, VALIDITY_MINUTES);
        emailProducer.produce(new SendEmailDto(request.email(),otp, LocaleContextHolder.getLocale().getLanguage()));
    }

    public RegisterRequest verify(VerifyRequest verifyRequest){
        RegisterRequest registerRequest = redisService.get(verifyRequest.otp());
        if (registerRequest != null && registerRequest.email().equals(verifyRequest.email())){
            redisService.delete(verifyRequest.otp());
            return registerRequest;
        }else {
            throw new BadRequestException(OTP_AND_EMAIL_DOES_NOT_MATCH);
        }
    }

}
