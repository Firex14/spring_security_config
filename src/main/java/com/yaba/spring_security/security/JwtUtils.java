package com.yaba.spring_security.security;

import com.yaba.spring_security.entity.UserAccount;
import com.yaba.spring_security.exceptions.TokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
@Component
public class JwtUtils {

    @Value("${security.jwt.secret}")
    private String JWT_SECRET;
    @Value("${security.jwt.expiration}")
    private Long JWT_EXPIRATION;
    @Value("${security.jwt.header}")
    private String JWT_HEADER;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaimsFromToken(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
        }catch (MalformedJwtException e){
            throw new TokenException("MALFORMED_TOKEN");
        }catch (ExpiredJwtException e){
            throw new TokenException("EXPIRED_TOKEN");
        }catch (UnsupportedJwtException e){
            throw new TokenException(e.getMessage());
        }
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserAccount account) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("accountId", account.getId());
        claims.put("role", account.getAuthorities());

        return Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                .setId(account.getId().toString())
                .setClaims(claims)
                .setSubject(account.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(JWT_EXPIRATION, ChronoUnit.MINUTES)))
                .signWith(getSignInKey(),SignatureAlgorithm.HS256).compact();
    }
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())  && !isTokenExpired(token));
    }
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Optional<String> parseJwt(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                .flatMap(authorization -> {
                    if (!authorization.startsWith(JWT_HEADER)) {
                        throw new TokenException("MALFORMED_TOKEN");
                    } else {
                        String token = authorization.split(JWT_HEADER)[1].trim();
                        return Optional.of(token);
                    }
                }).or(Optional::empty);
    }
}
