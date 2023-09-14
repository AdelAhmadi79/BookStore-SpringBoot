package com.example.book_store.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@Component
public class JwtUtil {

    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String createRefreshToken(Authentication authentication) {
        String username = authentication.getName();
        LocalDate now = LocalDate.now();
        LocalDate expiration = LocalDate.now().plusDays(2);

        return Jwts.builder()
                .setIssuer("your-issuer")
                .setSubject(username)
                .setIssuedAt(Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(expiration.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .signWith(SECRET_KEY)
                .compact();
    }

    public static String createAccessToken(Authentication authentication) {
        String username = authentication.getName();
        Date now = new Date();

        // Create a Calendar instance and set it to the current date and time
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        // Add 2 hours to the current date and time
        calendar.add(Calendar.HOUR_OF_DAY, 2);

        // Get the expiration date as a Date
        Date expiration = calendar.getTime();

        return Jwts.builder()
                .setIssuer("your-issuer")
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SECRET_KEY)
                .compact();
    }

    public static Claims decodeToken(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            return null; // Token not found or invalid format
        }

        // Extract the token from the header
        String token = header.substring(7); // Remove "Bearer " prefix

        // Decode the token and return claims
        return Jwts.parserBuilder().
                setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
