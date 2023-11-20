package com.homelearn.back.like;


import com.homelearn.back.house.ApartMapper;
import com.homelearn.back.house.exception.HouseErrorCode;
import com.homelearn.back.house.exception.HouseException;
import com.homelearn.back.like.dto.LikeParam;
import com.homelearn.back.like.exception.LikeErrorCode;
import com.homelearn.back.like.exception.LikeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.homelearn.back.house.exception.HouseErrorCode.*;
import static com.homelearn.back.like.exception.LikeErrorCode.*;


@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService{

    private final LikeMapper likeMapper;
    private final ApartMapper apartMapper;
    @Override
    public void addLike(LikeParam param) {
        apartMapper.findApartByApartCode(param.getAptCode())
                .orElseThrow(()-> new HouseException(NOT_EXISTS_HOUSE));
        likeMapper.findLike(param).ifPresent(
                value -> {throw new LikeException(ALREADY_IN_LIKE_APART);
                });
        likeMapper.addLike(param);
    }

    @Override
    public void deleteLike(LikeParam param) {
        apartMapper.findApartByApartCode(param.getAptCode())
                .orElseThrow(()-> new HouseException(NOT_EXISTS_HOUSE));
        likeMapper.findLike(param)
                .orElseThrow(()-> new LikeException(NOT_EXISTS_LIKE_APART));
        likeMapper.deleteLike(param);
    }
}
