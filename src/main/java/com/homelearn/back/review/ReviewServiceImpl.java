package com.homelearn.back.review;


import com.homelearn.back.house.ApartMapper;
import com.homelearn.back.house.exception.HouseErrorCode;
import com.homelearn.back.house.exception.HouseException;
import com.homelearn.back.review.dto.AddReviewParam;
import com.homelearn.back.review.dto.DeleteReviewParam;
import com.homelearn.back.review.dto.FindListReviewInputSpec;
import com.homelearn.back.review.dto.FindReviewParam;
import com.homelearn.back.review.entity.ReviewJoinUser;
import com.homelearn.back.review.exception.ReviewErrorCode;
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
        reviewMapper.addReview(param);
    }

    @Override
    public void deleteReview(DeleteReviewParam param) {
        reviewMapper.findReview(FindReviewParam.builder()
                        .id(param.getId())
                        .userId(param.getUserId())
                        .build())
                .orElseThrow(()-> new ReviewException(NOT_EXISTS_REVIEW));
        reviewMapper.deleteReview(param);
    }

    @Override
    public List<ReviewJoinUser> findReviewListByAptCode(FindListReviewInputSpec inputSpec) {
        apartMapper.findApartByApartCode(inputSpec.getAptCode())
                .orElseThrow(()-> new HouseException(NOT_EXISTS_HOUSE));
        return reviewMapper.findReviewListByAptCode(inputSpec);
    }
}
