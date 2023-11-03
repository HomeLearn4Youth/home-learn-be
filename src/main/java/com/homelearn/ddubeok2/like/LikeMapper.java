package com.homelearn.ddubeok2.like;

import com.homelearn.ddubeok2.like.dto.LikeInputForm;
import com.homelearn.ddubeok2.like.dto.LikeOutPutForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LikeMapper {
    void addLike(LikeInputForm likeForm);
    List<LikeOutPutForm> findLikeListByUserId(Long userId);
    void deleteLike(LikeInputForm likeForm);
}
