package com.ascpm.hofund.jwt.comm.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.text.MessageFormat;
import java.util.Base64;
import java.util.Map;

public class JwtUtils {
    private static final String TYPE = "JWT";
    private static final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.RS256;

    public static String getAlgorithm() {
        return ALGORITHM.getValue();
    }

    public static KeyPair getKeyPair() {
        Keys.keyPairFor(ALGORITHM).getPublic().getFormat();
        return Keys.keyPairFor(ALGORITHM);
    }

    public static String getPem(PublicKey key) {
        return MessageFormat.format(
                "{0}\n{1}\n{2}",
                "-----BEGIN PUBLIC KEY-----"
                , Base64.getEncoder().encodeToString(key.getEncoded()),
                "-----END PUBLIC KEY-----"
        );
    }

    public static String getToken(PrivateKey key, String issuer) {
        return Jwts.builder()
                .signWith(key, ALGORITHM)
                .setHeaderParams(Map.of(
                        "typ", TYPE,
                        "alg", ALGORITHM.getValue())
                )
                .setIssuer(issuer)
                .compact();
    }
}
