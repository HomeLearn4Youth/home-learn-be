package com.homelearn.back.review.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Review {
    private Long id;
    private Long aptCode;
    private String content;
    private Long userId;
    private String createTime;
}
