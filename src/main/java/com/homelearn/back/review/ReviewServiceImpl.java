package com.homelearn.back.review;


import com.homelearn.back.review.dto.AddReviewParam;
import com.homelearn.back.review.dto.DeleteReviewParam;
import com.homelearn.back.review.dto.FindListReviewInputSpec;
import com.homelearn.back.review.entity.ReviewJoinUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewMapper reviewMapper;

    @Override
    public void addReview(AddReviewParam param) {
        reviewMapper.addReview(param);
    }

    @Override
    public void deleteReview(DeleteReviewParam param) {
        reviewMapper.deleteReview(param);
    }

    @Override
    public List<ReviewJoinUser> findReviewListByAptCode(FindListReviewInputSpec inputSpec) {
        return reviewMapper.findReviewListByAptCode(inputSpec);
    }
}
