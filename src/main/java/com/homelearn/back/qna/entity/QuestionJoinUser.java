package com.homelearn.back.qna.entity;

import com.homelearn.back.user.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionJoinUser {
    private Long id;

    private String title;
    private String question;
    private String answer;
    private String questionCreateTime;
    private String answerCreateTime;

    private Long userId;

    private String email;
    private String name;
    private String provider;
    private UserRole role;
}
