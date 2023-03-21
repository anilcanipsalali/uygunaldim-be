package com.uygunaldim.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimeZone;

@Component
public class JwtProvider {
    @Value("${uygunaldim.security.token.secret}")
    private String appSecret;
    @Value("${uygunaldim.security.token.expires-in}")
    private Long expiresIn;

    public String generateToken(Authentication auth) {
        JwtUserDetails userDetails = (JwtUserDetails) auth.getPrincipal();
        return Jwts.builder()
                .setSubject(Long.toString(userDetails.getId()))
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(expiresIn, ChronoUnit.DAYS)))
                .signWith(SignatureAlgorithm.HS512, appSecret).compact();
    }

    public String generateTokenByUserId(Long userId) {
        return Jwts.builder()
                .setSubject(Long.toString(userId))
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(expiresIn, ChronoUnit.DAYS)))
                .signWith(SignatureAlgorithm.HS512, appSecret).compact();
    }

    public String getTokenExpiryDate(String token) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss, z");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+3"));
        return formatter.format(Jwts.parser()
                .setSigningKey(appSecret)
                .parseClaimsJws(token)
                .getBody().getExpiration());
    }

    public Long getUserIdFromJwt(String token) {
        return Long.parseLong(Jwts.parser()
                .setSigningKey(appSecret)
                .parseClaimsJws(token).getBody()
                .getSubject());
    }

    public boolean validateToken(String token) {
        try {
            Date expiration = Jwts.parser().setSigningKey(appSecret).parseClaimsJws(token).getBody().getExpiration();
            return !expiration.before(Date.from(Instant.now()));
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException
                | IllegalArgumentException ex) {
            return false;
        }
    }
}
