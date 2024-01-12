package com.ugurukku.linkshortener.util;

import com.ugurukku.linkshortener.model.property.SecurityProperty;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public final class PublicPrivateKeyUtil {

    private final SecurityProperty securityProperties;

    @Getter
    private static PrivateKey privateKey;
    @Getter
    private static PublicKey publicKey;

    public PublicPrivateKeyUtil(SecurityProperty securityProperties) {
        this.securityProperties = securityProperties;
        privateKey = preparePrivateKey();
        publicKey = preparePublicKey();
    }

    private PrivateKey preparePrivateKey() {
        try {
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(securityProperties.getJwt().getPrivateKey()));
            return kf.generatePrivate(keySpecPKCS8);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    private PublicKey preparePublicKey() {
        try {
            KeyFactory kf = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(securityProperties.getJwt().getPublicKey()));
            return kf.generatePublic(keySpecX509);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

}
