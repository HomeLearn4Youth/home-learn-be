package com.homelearn.back.common.provider;

import com.homelearn.back.common.config.JwtProperties;
import com.homelearn.back.common.util.JwtUtils;
import com.homelearn.back.user.UserService;
import com.homelearn.back.user.entity.User;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Slf4j
@Component
@RequiredArgsConstructor
public class JWTProvider {
    private final JwtUtils jwtUtils;
    private final JwtProperties jwtProperties;
    private String secretKey;
    private final UserService userService;

    @PostConstruct
    public void init() {
        this.secretKey = jwtProperties.getJwtSecretKey();
    }

    public boolean validateToken(final String token){
        try{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        }catch (SignatureException | MalformedJwtException e){
            log.info("exception : 잘못된 jwt signature");
        }catch (ExpiredJwtException e){
            log.info("exception : 기간 만료");
        }catch (UnsupportedJwtException e){
            log.info("exception : 지원되지 않는 jwt 토큰");
        }catch (IllegalArgumentException e){
            log.info("exception : 잘못된 jwt 토큰");
        }
        return false;
    }

    public Authentication getAuthentication(final String token) {
        // 토큰 복호화
        Claims claims = jwtUtils.parseClaims(token);
        log.info("token_claims : " + claims.toString());

        log.info(claims.get("id").toString());
        final Long userId = Long.parseLong(claims.get("id").toString());
        if (claims.get("role") == null) {
            throw new BadCredentialsException("권한 정보가 없는 토큰입니다.");
        }
        // 토큰에 맵핑되는 사용자 정보 데이터베이스에서 조회
        final User user = userService.findByIdUser(userId);
        log.info("user : " + user.toString());

        // 사용자의 권한 목록 설정
        // 클레임에서 권한 정보 가져오기
        final Collection<? extends GrantedAuthority> authorities = Stream.of(
                        "ROLE_"+claims.get("role").toString())
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        // Authentication 객체 생성 (사용자, 자격 증명, 권한 목록 포함)
        return new UsernamePasswordAuthenticationToken(user, userId, authorities);
    }
}