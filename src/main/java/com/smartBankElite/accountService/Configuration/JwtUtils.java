package com.smartBankElite.accountService.Configuration;

import com.smartBankElite.accountService.DTO.CacheDTO;
import com.smartBankElite.accountService.Utlis.SmartBankEliteConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtUtils  {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    public String extractUsername(String token) {
        return extractClaim(token, claims -> claims.get(SmartBankEliteConstants.USERNAME.getValue(), String.class));
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(String token) {
        return !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public CacheDTO getCacheDTOFromToken(HttpServletRequest httpServletRequest) {
        Claims claims = extractAllClaims(httpServletRequest.getHeader(SmartBankEliteConstants.AUTHORIZATION.getValue()).substring(7));
        CacheDTO cacheDTO = new CacheDTO();
        cacheDTO.setUserId(claims.get(SmartBankEliteConstants.USER_ID.getValue(), Integer.class));
        cacheDTO.setUserName(claims.get(SmartBankEliteConstants.USERNAME.getValue(), String.class));
        cacheDTO.setEmailId(claims.get(SmartBankEliteConstants.EMAIL_ID.getValue(), String.class));
        cacheDTO.setName(claims.get(SmartBankEliteConstants.NAME.getValue(), String.class));
        cacheDTO.setCreatedAt(claims.get(SmartBankEliteConstants.CREATED_AT.getValue(), Long.class));
        return cacheDTO;
    }
}
