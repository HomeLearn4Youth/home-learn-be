package com.homelearn.back.common.provider;

import com.homelearn.back.common.config.JwtProperties;
import com.homelearn.back.common.exception.JwtErrorCode;
import com.homelearn.back.common.exception.JwtException;
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

    public void validateToken(final String token){
        try{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        }catch (SignatureException | MalformedJwtException e){
            log.debug("exception : 잘못된 jwt signature");
            throw new JwtException(JwtErrorCode.TOKEN_SIGNATURE_ERROR);
        }catch (ExpiredJwtException e){
            log.debug("exception : 기간 만료");
            throw new JwtException(JwtErrorCode.EXPIRED_TOKEN);
        }catch (UnsupportedJwtException e){
            log.debug("exception : 지원되지 않는 jwt 토큰");
            throw new JwtException(JwtErrorCode.NOT_SUPPORT_TOKEN);
        }catch (IllegalArgumentException e){
            log.debug("exception : 잘못된 jwt 토큰");
            throw new JwtException(JwtErrorCode.INVALID_TOKEN);
        }
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
        // Authentication 객체 생성 (UserDetails 객체와 권한 목록 포함)
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }
}
