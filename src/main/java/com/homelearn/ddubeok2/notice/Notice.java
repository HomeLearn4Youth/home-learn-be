package com.homelearn.ddubeok2.notice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notice {
    Long id;
    String title;
    String content;
    int viewCount;
    String creationTime;
    Long writerId;
}
