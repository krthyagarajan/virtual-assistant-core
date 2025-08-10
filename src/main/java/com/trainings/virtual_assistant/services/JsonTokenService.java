package com.trainings.virtual_assistant.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trainings.virtual_assistant.config.Constants;
import com.trainings.virtual_assistant.login.exception.JwtSigningException;
import com.trainings.virtual_assistant.users.UserDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class JsonTokenService {

    private ObjectMapper objectMapper;

    public String generateAccessToken(UserDto user)  {

        JwtClaims jwtClaims = new JwtClaims();
        jwtClaims.setGeneratedJwtId();
        jwtClaims.setIssuer(Constants.SIGNING_APP);
        jwtClaims.setExpirationTimeMinutesInTheFuture(5);
        jwtClaims.setIssuedAtToNow();
        jwtClaims.setSubject("login token");

        Map<String,Object> userMap = objectMapper.convertValue(user, Map.class);
        jwtClaims.setClaim("claims_data", userMap);

        String signedToken = null;
        try {
            signedToken = signJWT(jwtClaims);
        } catch (JoseException e) {
            throw new JwtSigningException(e.getMessage());
        }

        return signedToken;
    }

    public String signJWT(JwtClaims jwtClaims) throws JoseException {

        HmacKey key = new HmacKey(Constants.SECRET_SIGNING_KEY.getBytes());

        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(jwtClaims.toJson());
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
        jws.setKey(key);

        return jws.getCompactSerialization();
    }


    public <T> T  getPayloadFromSignedToken(String signedToken, Class<T> returnClass) throws InvalidJwtException {

        T obj = null;

        HmacKey key = new HmacKey(Constants.SECRET_SIGNING_KEY.getBytes());

        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime()
                .setAllowedClockSkewInSeconds(30)
                .setRequireSubject()
                .setVerificationKey(key) // or .setSecretKey for HS256
                .build();


        JwtClaims claims = jwtConsumer.processToClaims(signedToken);

        ObjectMapper objMapper = new ObjectMapper();
        obj = objMapper.convertValue(claims.getClaimValue("claims_data"), returnClass);

        return obj;
    }
}
