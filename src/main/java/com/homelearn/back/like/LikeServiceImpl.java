package com.homelearn.back.like;

import com.homelearn.back.house.dto.ApartInfoOutput;
import com.homelearn.back.like.dto.LikeInputForm;
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
    public List<ApartInfoOutput> findLikeListByUserId(Long userId) {
        return likeMapper.findLikeListByUserId(userId);
    }

    @Override
    public void deleteLike(LikeInputForm likeForm) {
        likeMapper.deleteLike(likeForm);
    }
}
