package com.homelearn.back.like;

import com.homelearn.back.house.dto.ApartInfoOutput;
import com.homelearn.back.like.dto.LikeInputForm;

import java.util.List;

public interface LikeService {
    void addLike(LikeInputForm likeForm);
    List<ApartInfoOutput> findLikeListByUserId(Long userId);
    void deleteLike(LikeInputForm likeForm);
}
