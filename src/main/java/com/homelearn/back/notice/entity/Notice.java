package com.homelearn.back.notice.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notice {
    Long id;
    String title;
    String content;
    int viewCount;
    String creatTime;
    Long writerId;
}
