package com.homelearn.back.review;

import com.homelearn.back.review.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/add/{userId}")
    public ResponseEntity addReview(
            @RequestBody ReviewInputSpec input,
            @PathVariable("userId") Long userId
            ){
        reviewService.addReview(
                AddReviewParam.builder()
                        .content(input.getContent())
                        .aptCode(input.getAptCode())
                        .userId(userId)
                        .build()
        );
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{reviewId}/{userId}")
    public ResponseEntity deleteReview(
            @ModelAttribute ReviewInputSpec input,
            @PathVariable("reviewId") Long reviewId,
            @PathVariable("userId") Long userId
    ){
        reviewService.deleteReview(
                DeleteReviewParam.builder()
                        .id(reviewId)
                        .userId(userId)
                        .build()
        );
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findlist")
    public ResponseEntity<List<ReviewOutputSpec>> findReviewList(
            @ModelAttribute FindListReviewInputSpec inputSpec
    ){
        return ResponseEntity.ok().body(
                reviewService.findReviewListByAptCode(inputSpec)
                        .stream()
                        .map(m -> ReviewOutputSpec.builder()
                                .writerId(m.getUserId())
                                .writerName(m.getName())
                                .createTime(m.getCreateTime())
                                .reviewId(m.getReviewId())
                                .content(m.getContent())
                                .build())
                        .collect(Collectors.toList()
                        )
                );
    }
}
