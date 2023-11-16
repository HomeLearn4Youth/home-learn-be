package com.homelearn.back.user;

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
        log.info("token : "+token);
        String signature=token.split("\\.")[2];
        log.info("signature : "+signature);
        RefreshToken refreshToken=refreshMapper.match(signature);
        if(refreshToken==null){
            return null;
        }
        log.info(refreshToken.toString());
        log.info(jwtUtils.parseClaims(token).get("id").toString());
        log.info(refreshToken.getUserId().toString());
        if(Long.valueOf(jwtUtils.parseClaims(token).get("id").toString())==refreshToken.getUserId()){ //db에 기록된 user 와 token에 기록된 user 일치
            log.info("refresh access token(1) : same user, "+refreshToken.getUserId());
            if(jwtUtils.validateToken(token)) { //유효한 토큰
                log.info("refresh access token(2) : valid token");
                User user = userMapper.findByIdUser(refreshToken.getUserId());
                String accessToken=jwtUtils.issueAccessToken(user);
                log.info("refresh access token(3) : issue access token,  "+accessToken);
                return accessToken;
            }
            log.info("refresh access token(2) : invalid token");
        }
        return null;
    }

}
