package com.prototype.classyBackEnd.common.utils;

import com.prototype.classyBackEnd.common.model.JwtAccessToken;
import com.prototype.classyBackEnd.common.model.JwtRefreshToken;
import com.prototype.classyBackEnd.common.model.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.UUID;

@Component
public class GeneralJwtUtils implements JwtUtils {

    private static final long JWT_ACCESS_TOKEN_EXPIRE_MILLISECOND = 1000L * 60 * 60;
    private static final long JWT_REFRESH_TOKEN_EXPIRE_MILLISECOND = 1000L * 60 * 60 * 24 * 15;

    private static String jwtSecretKey;

    public static JwtToken generateJwtTokenBy(Long userNo) {
        return new JwtToken(generateJwtAccessTokenBy(userNo), generateJwtRefreshTokenBy(userNo));
    }

    public static JwtAccessToken generateJwtAccessTokenBy(Long userNo) {
        Long expireMillisecond = System.currentTimeMillis() + JWT_ACCESS_TOKEN_EXPIRE_MILLISECOND;
        String accessToken = Jwts.builder()
                .setSubject("classy-access")
                .setHeaderParam("typ", "JWT")
                .claim("userNo", userNo)
                .claim("id", UUID.randomUUID().toString())
                .setExpiration(new Date(expireMillisecond))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey)
                .compact();
        return new JwtAccessToken(accessToken, expireMillisecond);
    }

    public static JwtRefreshToken generateJwtRefreshTokenBy(Long userNo) {
        Long expireMillisecond = System.currentTimeMillis() + JWT_REFRESH_TOKEN_EXPIRE_MILLISECOND;
        String accessToken = Jwts.builder()
                .setSubject("classy-refresh")
                .setHeaderParam("typ", "JWT")
                .claim("userNo", userNo)
                .claim("id", UUID.randomUUID().toString())
                .setExpiration(new Date(expireMillisecond))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey)
                .compact();
        return new JwtRefreshToken(accessToken, expireMillisecond);
    }

    @Value("${spring.jwt.general.secret-key}")
    public void setJwtSecretKey(String jwtSecretKey) { GeneralJwtUtils.jwtSecretKey = jwtSecretKey; }

    @Override
    public Claims getClaimsBy(String token) {
        return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token.replaceFirst("Bearer ", "")).getBody();
    }

}
