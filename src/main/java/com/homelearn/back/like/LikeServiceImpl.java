package com.homelearn.back.like;


import com.homelearn.back.like.dto.LikeParam;
import com.homelearn.back.like.exception.LikeErrorCode;
import com.homelearn.back.like.exception.LikeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.homelearn.back.like.exception.LikeErrorCode.*;


@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService{

    private final LikeMapper likeMapper;

    @Override
    public void addLike(LikeParam param) {
        likeMapper.findLike(param).ifPresent(
                value -> {throw new LikeException(ALREADY_IN_LIKE_ITEM);
                });
        likeMapper.addLike(param);
    }

    @Override
    public void deleteLike(LikeParam param) {
        likeMapper.findLike(param)
                .orElseThrow(()-> new LikeException(NOT_EXISTS_LIKE_ITEM));
        likeMapper.deleteLike(param);
    }
}
