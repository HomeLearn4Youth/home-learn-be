package com.homelearn.back.like;


import com.homelearn.back.like.dto.LikeParam;
import com.homelearn.back.like.entity.Like;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;


@Mapper
public interface LikeMapper {
    Optional<Like> findLike(LikeParam param);
    Optional<Like> findLikeByLikeId(Long likeId);
    void addLike(LikeParam param);
    void deleteLike(LikeParam param);
}
