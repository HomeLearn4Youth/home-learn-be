package com.homelearn.ddubeok2.like;

import com.homelearn.ddubeok2.like.dto.LikeInputForm;
import com.homelearn.ddubeok2.like.dto.LikeOutPutForm;

import java.util.List;

public interface LikeService {
    void addLike(LikeInputForm likeForm);
    List<LikeOutPutForm> findLikeListByUserId(Long userId);
    void deleteLike(LikeInputForm likeForm);
}
