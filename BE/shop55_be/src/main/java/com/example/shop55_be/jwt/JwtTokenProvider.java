package com.example.shop55_be.jwt;

import com.example.shop55_be.security.CustomUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${ra.jwt.sercret}")
    private String JWT_SERCRET;
    @Value("${ra.jwt.expiration}")
    private long JWT_EXPIRATION;

    public String generateToken(CustomUser customUser){
        Date now = new Date();
        Date dateExpired = new Date(now.getTime()+JWT_EXPIRATION);
        return Jwts.builder()
                .setSubject(customUser.getEmail())
                .setIssuedAt(now)
                .setExpiration(dateExpired)
                .signWith(SignatureAlgorithm.HS512,JWT_SERCRET)
                .compact();
    }
    public String getMailFromJwt(String token){
        Claims claims = Jwts.parser().setSigningKey(JWT_SERCRET)
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    // validate token
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(JWT_SERCRET).parseClaimsJws(token);
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
