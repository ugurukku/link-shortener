package com.ugurukku.linkshortener.service.helper;

import com.ugurukku.linkshortener.util.ShortLinkGenerator;
import org.springframework.stereotype.Component;

@Component
public class LinkHelper {

    public String generateShortLink(){
        return ShortLinkGenerator.generate();
    }

}
