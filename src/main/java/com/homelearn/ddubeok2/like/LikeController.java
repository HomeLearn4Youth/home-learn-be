package com.homelearn.ddubeok2.like;

import com.homelearn.ddubeok2.like.dto.LikeInputForm;
import com.homelearn.ddubeok2.like.dto.LikeOutPutForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/add")
    public ResponseEntity<?> addLike(
            @RequestBody LikeInputForm likeForm
            ){
        likeService.addLike(likeForm);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/list")
    public ResponseEntity<List<LikeOutPutForm>> showLikeListByUserId(
            @PathVariable("userId") Long userId
    ){
        List<LikeOutPutForm> likeListByUserId = likeService.findLikeListByUserId(userId);
        return ResponseEntity.ok().body(likeListByUserId);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteLike(
        @RequestBody LikeInputForm likeForm
    ){
        likeService.deleteLike(likeForm);
        return ResponseEntity.ok().build();
    }

}
