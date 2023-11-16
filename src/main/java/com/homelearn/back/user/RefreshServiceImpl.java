package com.homelearn.back.user;

import com.homelearn.back.common.exception.JwtErrorCode;
import com.homelearn.back.common.exception.JwtException;
import com.homelearn.back.common.util.JwtUtils;
import com.homelearn.back.user.entity.RefreshToken;
import com.homelearn.back.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshServiceImpl implements RefreshService {
    private final RefreshMapper refreshMapper;
    private final JwtUtils jwtUtils;
    private final UserMapper userMapper;
    @Override
    public void save(String token,Long userId) {
        String[] tokenSplit=token.split("\\.");
        refreshMapper.save(new RefreshToken(tokenSplit[2],userId));
    }

    @Override
    public String match(String token) {
        log.debug("token : "+token);
        String signature=token.split("\\.")[2];
        log.debug("signature : "+signature);
        RefreshToken refreshToken=refreshMapper.match(signature);
        if(refreshToken==null){
            throw new JwtException(JwtErrorCode.NO_TOKEN);
        }
        log.debug(refreshToken.toString());
        log.debug(jwtUtils.parseClaims(token).get("id").toString());
        log.debug(refreshToken.getUserId().toString());
        if(Long.valueOf(jwtUtils.parseClaims(token).get("id").toString())==refreshToken.getUserId()){ //db에 기록된 user 와 token에 기록된 user 일치
            log.debug("refresh access token(1) : same user, "+refreshToken.getUserId());
            if(jwtUtils.validateToken(token)) { //유효한 토큰
                log.debug("refresh access token(2) : valid token");
                User user = userMapper.findByIdUser(refreshToken.getUserId());
                String accessToken=jwtUtils.issueAccessToken(user);
                log.debug("refresh access token(3) : issue access token,  "+accessToken);
                return accessToken;
            }
            log.debug("refresh access token(2) : invalid token");
        }
        throw new JwtException(JwtErrorCode.INVALID_TOKEN);
    }

}
