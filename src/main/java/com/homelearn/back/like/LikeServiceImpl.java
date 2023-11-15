package com.homelearn.back.like;


import com.homelearn.back.like.dto.LikeParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService{

    private final LikeMapper likeMapper;

    @Override
    public void addLike(LikeParam param) {
        likeMapper.addLike(param);
    }

    @Override
    public void deleteLike(LikeParam param) {
        likeMapper.deleteLike(param);
    }
}
