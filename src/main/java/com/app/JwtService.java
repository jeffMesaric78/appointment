package com.app;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class JwtService {

    // A secure, long secret key (at least 256 bits for HS256)
    // This should ideally be loaded from an application configuration file
    //private static final String SECRET_KEY = "your_very_long_and_secure_secret_key_that_is_at_least_256_bits_long";
    private static final String SECRET_KEY = "eW91cl92ZXJ5X2xvbmdfYW5kX3NlY3VyZV9zZWNyZXRfa2V5X3RoYXRfaXNfYXRfbGVhc3RfMjU2X2JpdHNfbG9uZw==";
    //private static final String SECRET_KEY_STRING = "yourSuperSecretKeyThatIsAtLeast256BitsLongAndSecure";
    //private static final SIGNING_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(Base64.getEncoder().encodeToString(SECRET_KEY_STRING.getBytes())));



    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    private String createToken(Map<String, Object> claims, String userName) {
        Instant now = Instant.now();
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName) // The 'sub' claim
                .setIssuedAt(Date.from(now)) // The 'iat' claim
                .setExpiration(Date.from(now.plus(5L, ChronoUnit.MINUTES))) // The 'exp' claim
                .signWith(key, SignatureAlgorithm.HS256) // Sign the JWT with the secret key and algorithm
                .compact(); // Build and serialize the JWT to a compact, URL-safe string
    }

    // Additional methods would be needed for token validation (isTokenValid, extractUserName, etc.)

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            System.out.println("Token is valid. Subject: " + claimsJws.getBody().getSubject());
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            System.out.println("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT token: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT token: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: " + e.getMessage());
        }
        return false;
    }

}
