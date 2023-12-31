package com.ugurukku.linkshortener.service.helper;

import com.ugurukku.linkshortener.util.LinkGeneratorUtil;
import org.springframework.stereotype.Component;

@Component
public class LinkHelper {

    public String generateShortLink(){
        return LinkGeneratorUtil.generate();
    }

}
