package com.homelearn.back.notice.entity;

import com.homelearn.back.user.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeJoinMember {
    private Long id;
    private String title;
    private String content;
    private int viewCount;
    private String creatTime;
    private Long writerId; //MemberId 로 조인
    private String writerPassword;
    private String writerEmail;
    private String writerName;
    private UserRole writerRole;
    private String provider;
}
