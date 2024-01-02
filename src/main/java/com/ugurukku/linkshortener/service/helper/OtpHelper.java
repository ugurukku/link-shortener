package com.ugurukku.linkshortener.service.helper;

import com.ugurukku.linkshortener.util.OtpGeneratorUtil;
import org.springframework.stereotype.Component;

@Component
public class OtpHelper {

    public String generateOtp(){
        return OtpGeneratorUtil.generate();
    }

}
