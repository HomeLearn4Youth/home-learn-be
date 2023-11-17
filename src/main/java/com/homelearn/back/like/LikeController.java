package com.homelearn.back.like;

import com.homelearn.back.common.util.MessageUtil;
import com.homelearn.back.like.dto.LikeParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    /**
     * sec 적용 이후 @AuthenticationPrincipal LoginUser 를 통해 user 정보를 가져와서 넣어야함
     * 현재는 userId를 PathVariable에서 가져오는 방식
     */
    @PostMapping("/add/{apartCode}/{userId}")
    public ResponseEntity<MessageUtil> addLike(
            @PathVariable("apartCode") Long code,
            @PathVariable("userId") Long userId
            ){
        likeService.addLike(
                LikeParam.builder()
                        .aptCode(code)
                        .userId(userId)
                        .build()
        );
        return ResponseEntity.ok().body(MessageUtil.success());
    }


    @DeleteMapping("/delete/{apartCode}/{userId}")
    public ResponseEntity<MessageUtil> deleteLike(
        @PathVariable("apartCode") Long code,
        @PathVariable("userId") Long userId
    ){
        likeService.deleteLike(
                LikeParam.builder()
                        .aptCode(code)
                        .userId(userId)
                        .build()
        );
        return ResponseEntity.ok().body(MessageUtil.success());
    }

}
