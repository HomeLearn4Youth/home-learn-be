package com.homelearn.back.notice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NoticeParam {
    private Long id;
    private String title;
    private String content;
    private int viewCount;
    private String creatTime;
    private Long writerId;
}
