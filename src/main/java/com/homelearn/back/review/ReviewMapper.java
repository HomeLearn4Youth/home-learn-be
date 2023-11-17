package com.homelearn.back.review;


import com.homelearn.back.review.dto.AddReviewParam;
import com.homelearn.back.review.dto.FindListReviewInputSpec;
import com.homelearn.back.review.dto.FindReviewParam;
import com.homelearn.back.review.entity.Review;
import com.homelearn.back.review.entity.ReviewJoinUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReviewMapper {
    void addReview(AddReviewParam input);
    void deleteReview(Long reviewId);
    Optional<Review> findReview(FindReviewParam input);
    List<ReviewJoinUser> findReviewListByAptCode(FindListReviewInputSpec inputSpec);
}
