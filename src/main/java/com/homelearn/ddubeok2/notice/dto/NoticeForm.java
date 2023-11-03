package com.homelearn.ddubeok2.notice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeForm {
    Long noticeId;
    String title;
    String content;
    Long writerId;
}
