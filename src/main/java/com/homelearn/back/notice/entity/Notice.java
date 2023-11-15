package com.homelearn.back.notice.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notice {
    private Long id;
    private String title;
    private String content;
    private int viewCount;
    private String creatTime;
    private Long writerId;
}
