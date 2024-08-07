package com.fullCar.FullCar.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fullCar.FullCar.exception.AuthenticationException;
import com.fullCar.FullCar.exception.GenerateTokenException;
import com.fullCar.FullCar.model.Account;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import com.auth0.jwt.exceptions.JWTCreationException;

@Service
@RequiredArgsConstructor
public class TokenService {

    public String generateToken(Account account) {
        try {
            var algorithm = Algorithm.HMAC256("12345678");
            return JWT.create()
                    .withIssuer("Full car")
                    .withSubject(account.getEmail())
                    .withClaim("id", account.getId())
                    .sign(algorithm);
        } catch (JWTCreationException exception ) {
        throw new GenerateTokenException("Unable to generate token: " + exception);
        }
    }
    public String getToken(HttpServletRequest request){
        var authorizationHeader= request.getHeader("Authorization");
        if(authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
    public String recoverContentToken(String token){
        try{
            var algorithm = Algorithm.HMAC256("12345678");
            return JWT.require(algorithm)
                    .withIssuer("Full car")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException exception){
            throw new AuthenticationException("invalid or expired token!");
        }
    }

}
