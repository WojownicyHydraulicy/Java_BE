package org.bartoszwojcik.hydropol.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtUtil {
    @Value("${jwt.expiration}")
    private Long expiration;

    private Key secret;

    public JwtUtil(@Value("${jwt.secret}") String secretString) {
        secret = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String email) {
        return Jwts.builder()
                // .subject(email)
                .claim("email", email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secret)
                .compact();
    }

    public boolean isValidToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token);

        return !claimsJws.getBody().getExpiration().before(new Date());
    }

    public String getEmail(String token) {
        //  return getClaim(token, Claims::getSubject);
        return getClaim(token, claims -> claims.get("email", String.class));
    }

    private <T> T getClaim(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsTFunction.apply(claims);
    }
}
