package com.homelearn.back.like;

import com.homelearn.back.common.util.MessageUtil;
import com.homelearn.back.like.dto.LikeParam;
import com.homelearn.back.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    /**
     * sec 적용 이후 @AuthenticationPrincipal LoginUser 를 통해 user 정보를 가져와서 넣어야함
     * 현재는 userId를 PathVariable에서 가져오는 방식
     */
    @PostMapping("/add/{apartCode}")
    public ResponseEntity<MessageUtil> addLike(
            @PathVariable("apartCode") Long code,
            @AuthenticationPrincipal User user
            ){
        log.debug(user.toString());
        likeService.addLike(
                LikeParam.builder()
                        .aptCode(code)
                        .userId(user.getId())
                        .build()
        );
        return ResponseEntity.ok().body(MessageUtil.success());
    }


    @DeleteMapping("/delete/{apartCode}")
    public ResponseEntity<MessageUtil> deleteLike(
        @PathVariable("apartCode") Long code,
        @AuthenticationPrincipal User user
    ){
        likeService.deleteLike(
                LikeParam.builder()
                        .aptCode(code)
                        .userId(user.getId())
                        .build()
        );
        return ResponseEntity.ok().body(MessageUtil.success());
    }

}
