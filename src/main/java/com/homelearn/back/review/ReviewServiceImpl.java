package com.homelearn.back.review;


import com.homelearn.back.house.ApartMapper;
import com.homelearn.back.house.exception.HouseException;
import com.homelearn.back.review.dto.AddReviewParam;
import com.homelearn.back.review.dto.FindListReviewInputSpec;
import com.homelearn.back.review.dto.FindReviewParam;
import com.homelearn.back.review.entity.ReviewJoinUser;
import com.homelearn.back.review.exception.ReviewException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.homelearn.back.house.exception.HouseErrorCode.*;
import static com.homelearn.back.review.exception.ReviewErrorCode.*;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewMapper reviewMapper;
    private final ApartMapper apartMapper;
    @Override
    public void addReview(AddReviewParam param) {
        apartMapper.findApartByApartCode(param.getAptCode())
                .orElseThrow(()-> new HouseException(NOT_EXISTS_HOUSE));
        reviewMapper.addReview(param);
    }

    @Override
    public void deleteReview(Long reviewId, Long loginUserId) {
        if(reviewMapper.findReview(FindReviewParam.builder()
                        .id(reviewId)
                        .build())
                .orElseThrow(() -> new ReviewException(NOT_EXISTS_REVIEW))
                .getUserId()!=loginUserId) throw new ReviewException(FORBIDDEN_REVIEW);

        reviewMapper.deleteReview(reviewId);
    }

    @Override
    public List<ReviewJoinUser> findReviewListByAptCode(FindListReviewInputSpec inputSpec) {
        apartMapper.findApartByApartCode(inputSpec.getAptCode())
                .orElseThrow(()-> new HouseException(NOT_EXISTS_HOUSE));
        return reviewMapper.findReviewListByAptCode(inputSpec);
    }
}
