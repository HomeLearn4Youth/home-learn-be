package com.homelearn.back.review;

import com.homelearn.back.common.util.MessageUtil;
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
    public ResponseEntity<MessageUtil> addReview(
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
        return ResponseEntity.ok().body(MessageUtil.success());
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
        return ResponseEntity.ok().body(MessageUtil.success());
    }

    @GetMapping("/findlist")
    public ResponseEntity<MessageUtil<List<ReviewOutputSpec>>> findReviewList(
            @ModelAttribute FindListReviewInputSpec inputSpec
    ){
        return ResponseEntity.ok().body(MessageUtil.success(
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
                        ))
                );
    }
}
