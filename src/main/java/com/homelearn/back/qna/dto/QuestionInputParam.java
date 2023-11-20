package com.homelearn.back.qna.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class QuestionInputParam {
    private String title;
    private String question;
    private Long loginUserId;
}
