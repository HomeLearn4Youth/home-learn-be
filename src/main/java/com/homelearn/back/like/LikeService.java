package com.homelearn.back.like;


import com.homelearn.back.like.dto.LikeParam;


public interface LikeService {
    void addLike(LikeParam param);
    void deleteLike(LikeParam param);
}
