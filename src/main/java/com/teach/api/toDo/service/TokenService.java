package com.teach.api.toDo.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.teach.api.toDo.dto.res.LoginResDTO;
import com.teach.api.toDo.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${SECRET}")
    private String secret;

    public LoginResDTO generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        Instant expirationDate = getExpirationDate();

        String token = JWT.create()
                .withIssuer("3035teach/apiToDo")
                .withSubject(user.getUsername())
                .withExpiresAt(expirationDate)
                .sign(algorithm);

        return new LoginResDTO(
                "Bearer",
                token,
                expirationDate.toEpochMilli()
        );

    }

    public String ValidateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        String subject = JWT.require(algorithm)
                .withIssuer("3035teach/apiToDo")
                .build()
                .verify(token)
                .getSubject();

        return subject;

    }

    private Instant getExpirationDate() {
        return LocalDateTime.now()
                .plusHours(3)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}

