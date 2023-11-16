package com.homelearn.back.user;

public interface RefreshService {
    public void save(String token,Long userId);
    public String match(String token);
}
