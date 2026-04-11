package com.acharya.dikshanta.hospital.management.system.Hms.service;

import com.acharya.dikshanta.hospital.management.system.Hms.model.UserPrincipal;
import com.acharya.dikshanta.hospital.management.system.Hms.utils.Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final Utils utils;

    private SecretKey generateKeys() {
        byte[] byteStream = Decoders.BASE64.decode(utils.getJwt().getSecret());
        return Keys.hmacShaKeyFor(byteStream);
    }

    public String getJwt(UserPrincipal user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("userId", user.getUser().getId())
                .claim("role", user.getUser().getRole().name())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + utils.getJwt().getExpiry() * 60 * 1000))
                .signWith(generateKeys())
                .compact();

    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parser()
                .verifyWith(generateKeys())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();

    }

    public String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    private boolean isExpired(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getExpiration().before(new Date(System.currentTimeMillis()));
    }

    public boolean isValid(UserDetails userDetails, String token) {
        String username = extractUsername(token);
        return !isExpired(token) && username.equals(userDetails.getUsername());
    }

}
