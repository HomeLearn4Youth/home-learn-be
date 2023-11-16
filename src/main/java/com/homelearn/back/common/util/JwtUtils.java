package com.homelearn.back.common.util;

import com.homelearn.back.common.config.JwtProperties;
import com.homelearn.back.common.exception.JwtErrorCode;
import com.homelearn.back.common.exception.JwtException;
import com.homelearn.back.user.entity.User;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtils {
    private final JwtProperties jwtProperties;
    private String secretKey;
    @PostConstruct
    public void init() {
        this.secretKey = jwtProperties.getJwtSecretKey();
    }

    /**
     * ACCESS_TOKEN_DURATION=밀리초*초*분*시간*일
     * 7일
     */
    private static final long ACCESS_TOKEN_DURATION=1000L*60*60*24*7;
    public Date getExpiredTime(){
        ZoneId koreaZoneId = ZoneId.of("Asia/Seoul");
        ZonedDateTime currentTime = ZonedDateTime.now(koreaZoneId);
        ZonedDateTime expiredAt = currentTime.plus(Duration.ofMillis(ACCESS_TOKEN_DURATION));
        Instant instant = expiredAt.toInstant();
        // Instant를 Date로 변환
        return Date.from(instant);
    }
    public Date getCurrentTime(){
        ZoneId koreaZoneId = ZoneId.of("Asia/Seoul");
        ZonedDateTime currentTime = ZonedDateTime.now(koreaZoneId);
        Instant instant = currentTime.toInstant();
        // Instant를 Date로 변환
        return Date.from(instant);
    }
    public String issueRefreshToken(final User user){
        Date expiredAt=getExpiredTime();
        String token= Jwts.builder()
                .claim("id",user.getId())
                .setExpiration(expiredAt) //토큰 만료 시점
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();//토큰 생성
        return token;
    }
    public String issueAccessToken(final User user){
        Date expiredAt=getExpiredTime();
        String token= Jwts.builder()
                .claim("id",user.getId()) //클레임 설정 => payload
                .claim("name",user.getName())
                .claim("role",user.getRole())
                .setExpiration(expiredAt) //토큰 만료 시점
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();//토큰 생성
        return token;
    }
    public Claims parseClaims(final String token){
        try {
            return Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
    public boolean validateToken(final String token){
        try{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        }catch (SignatureException | MalformedJwtException e){
            log.info("exception : 잘못된 jwt signature");
            throw new JwtException(JwtErrorCode.TOKEN_SIGNATURE_ERROR);
        }catch (ExpiredJwtException e){
            log.info("exception : 기간 만료");
            throw new JwtException(JwtErrorCode.EXPIRED_TOKEN);
        }catch (UnsupportedJwtException e){
            log.info("exception : 지원되지 않는 jwt 토큰");
            throw new JwtException(JwtErrorCode.NOT_SUPPORT_TOKEN);
        }catch (IllegalArgumentException e){
            log.info("exception : 잘못된 jwt 토큰");
            throw new JwtException(JwtErrorCode.INVALID_TOKEN);
        }
    }
    public String getParseJwt(final String headerAuth){
        if(StringUtils.hasText(headerAuth)&&headerAuth.startsWith("Bearer")){
            return headerAuth.substring(7);
        }
        log.debug("no token");
        return null;
    }
}
