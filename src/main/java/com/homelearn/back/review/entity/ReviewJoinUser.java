package com.homelearn.back.review.entity;

import com.homelearn.back.user.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewJoinUser {
    private Long userId;

    private Long aptCode;
    private Long reviewId;
    private String content;
    private String createTime;

    private String password;
    private String email;
    private String name;
    private UserRole role;
}
