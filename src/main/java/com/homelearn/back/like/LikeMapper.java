package com.homelearn.back.like;


import com.homelearn.back.like.dto.LikeParam;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface LikeMapper {
    void addLike(LikeParam param);
    void deleteLike(LikeParam param);
}
