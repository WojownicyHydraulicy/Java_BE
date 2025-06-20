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

/**
 * Utility class for creating and validating JSON Web Tokens (JWT).
 * Uses the secret key and expiration time injected from application properties.
 */
@Configuration
public class JwtUtil {
    @Value("${jwt.expiration}")
    private Long expiration;

    private Key secret;

    /**
     * Constructs JwtUtil with the secret key used to sign JWTs.
     *
     * @param secretString the secret key as a string
     */
    public JwtUtil(@Value("${jwt.secret}") String secretString) {
        secret = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Generates a JWT token with the given email as a claim.
     * The token includes an expiration time based on configured expiration property.
     *
     * @param email the email to include in the token claims
     * @return generated JWT token as a String
     */
    public String generateToken(String email) {
        return Jwts.builder()
                .claim("email", email)
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(secret)
                .setHeaderParam("typ", "JWT")
                .compact();
    }

    /**
     * Validates if the JWT token is well-formed, signed correctly and not expired.
     *
     * @param token the JWT token string to validate
     * @return true if the token is valid, false otherwise
     */
    public boolean isValidToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token);

        return !claimsJws.getBody().getExpiration().before(new Date());
    }

    /**
     * Extracts the email claim from the given JWT token.
     *
     * @param token the JWT token string
     * @return the email contained in the token claims
     */
    public String getEmail(String token) {
        return getClaim(token, claims -> claims.get("email", String.class));
    }

    /**
     * Helper method to extract a claim from the token using a function.
     *
     * @param <T> the type of the claim to extract
     * @param token the JWT token string
     * @param claimsTFunction function extracting the claim from Claims
     * @return the extracted claim of type T
     */
    private <T> T getClaim(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsTFunction.apply(claims);
    }
}
