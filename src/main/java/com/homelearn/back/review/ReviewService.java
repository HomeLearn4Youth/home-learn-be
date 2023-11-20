package com.homelearn.back.review;


import com.homelearn.back.review.dto.AddReviewParam;
import com.homelearn.back.review.dto.FindListReviewInputSpec;
import com.homelearn.back.review.entity.ReviewJoinUser;

import java.util.List;

public interface ReviewService {
    void addReview(AddReviewParam input);
    void deleteReview(Long reviewId, Long loginUserId);
    List<ReviewJoinUser> findReviewListByAptCode(FindListReviewInputSpec inputSpec);
}