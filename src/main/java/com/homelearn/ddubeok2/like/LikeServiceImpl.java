package com.homelearn.ddubeok2.like;

import com.homelearn.ddubeok2.like.dto.LikeInputForm;
import com.homelearn.ddubeok2.like.dto.LikeOutPutForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService{

    private final LikeMapper likeMapper;

    @Override
    public void addLike(LikeInputForm likeForm) {
        likeMapper.addLike(likeForm);
    }

    @Override
    public List<LikeOutPutForm> findLikeListByUserId(Long userId) {
        return likeMapper.findLikeListByUserId(userId);
    }

    @Override
    public void deleteLike(LikeInputForm likeForm) {
        likeMapper.deleteLike(likeForm);
    }
}
