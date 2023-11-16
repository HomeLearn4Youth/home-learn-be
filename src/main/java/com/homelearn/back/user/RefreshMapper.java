package com.homelearn.back.user;

import com.homelearn.back.user.entity.RefreshToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RefreshMapper {
    public void save(RefreshToken token);
    public RefreshToken match(String signature);
}
