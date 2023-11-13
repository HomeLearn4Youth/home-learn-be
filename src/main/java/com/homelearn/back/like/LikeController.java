package com.homelearn.back.like;

import com.homelearn.back.house.dto.ApartInfoOutput;
import com.homelearn.back.like.dto.LikeInputForm;
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

    @GetMapping("/findlist/{userId}")
    public ResponseEntity<List<ApartInfoOutput>> showLikeListByUserId(
            @PathVariable("userId") Long userId
    ){
        return ResponseEntity.ok().body(likeService.findLikeListByUserId(userId));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteLike(
        @RequestBody LikeInputForm likeForm
    ){
        likeService.deleteLike(likeForm);
        return ResponseEntity.ok().build();
    }

}
