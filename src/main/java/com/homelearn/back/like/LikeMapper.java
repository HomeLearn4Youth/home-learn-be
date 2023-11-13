package com.homelearn.back.like;

import com.homelearn.back.house.dto.ApartInfoOutput;
import com.homelearn.back.like.dto.LikeInputForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LikeMapper {
    void addLike(LikeInputForm likeForm);
    List<ApartInfoOutput> findLikeListByUserId(Long userId);
    void deleteLike(LikeInputForm likeForm);
}
