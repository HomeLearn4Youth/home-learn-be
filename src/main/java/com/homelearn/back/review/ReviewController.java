package com.homelearn.back.review;

import com.homelearn.back.common.util.MessageUtil;
import com.homelearn.back.review.dto.*;
import com.homelearn.back.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/add")
    public ResponseEntity<MessageUtil> addReview(
            @RequestBody ReviewInputSpec input,
            @AuthenticationPrincipal User user
            ){
        reviewService.addReview(
                AddReviewParam.builder()
                        .content(input.getContent())
                        .aptCode(input.getAptCode())
                        .userId(user.getId())
                        .build()
        );
        return ResponseEntity.ok().body(MessageUtil.success());
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity deleteReview(
            @ModelAttribute ReviewInputSpec input,
            @PathVariable("reviewId") Long reviewId,
            @AuthenticationPrincipal User user
    ){
        reviewService.deleteReview(reviewId, user.getId());
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
