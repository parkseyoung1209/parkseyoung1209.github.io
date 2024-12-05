package com.mypage.config;

import com.mypage.model.vo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Configuration;


import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

@Configuration
public class TokenProvider {
    private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String create(User user) {
        return Jwts.builder()
                .signWith(secretKey)
                .setClaims(Map.of(
                        "userId", user.getUserId(),
                        "userPassword", user.getUserPassword(),
                        "userRole", user.getUserRole()
                ))
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
                .compact();
    }

    public User validate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return User.builder()
                .userId((String) claims.get("userId"))
                .userPassword((String) claims.get("userPassword"))
                .userRole((User.UserRole) claims.get("userRole"))
                .build();
    }
}
