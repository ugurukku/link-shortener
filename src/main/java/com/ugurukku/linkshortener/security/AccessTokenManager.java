package com.ugurukku.linkshortener.security;

import com.ugurukku.linkshortener.model.entity.User;
import com.ugurukku.linkshortener.model.property.SecurityProperty;
import com.ugurukku.linkshortener.util.PublicPrivateKeyUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.ugurukku.linkshortener.model.constants.TokenConstants.EMAIL_KEY;

@Component
@Slf4j
@RequiredArgsConstructor
public class AccessTokenManager {

    private final SecurityProperty securityProperty;

    public String generate(User user) {

        Claims claims = Jwts.claims();
        claims.put(EMAIL_KEY, user.getEmail());

        Date now = new Date();
        Date exp = new Date(now.getTime() + securityProperty.getJwt().getValidityTime());

        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .setIssuedAt(now)
                .setExpiration(exp)
                .addClaims(claims)
                .signWith(PublicPrivateKeyUtil.getPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
    }

    public Claims read(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(PublicPrivateKeyUtil.getPublicKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getEmail(String token) {
        return read(token).get(EMAIL_KEY, String.class);
    }

}