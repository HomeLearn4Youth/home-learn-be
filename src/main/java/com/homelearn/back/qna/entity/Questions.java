package com.homelearn.back.qna.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Questions {
    private Long id;
    private Long userId;
    private String title;
    private String question;
    private String answer;
    private String questionCreateTime;
    private String answerCreateTime;
}
