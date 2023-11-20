package com.homelearn.back.qna.dto;

import com.homelearn.back.qna.entity.QuestionJoinUser;
import com.homelearn.back.user.UserRole;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class QuestionOutputSpec {
    private Long questionId;

    private Long writerId;
    private String question;
    private String questionCreateTime;

    private String answer;
    private String answerCreateTime;

    private String writerEmail;
    private String writerName;
    private UserRole writerRole;

    public QuestionOutputSpec questionJoinUserToOutputSpec(QuestionJoinUser m){
        return QuestionOutputSpec.builder()
                .questionId(m.getId())
                .writerId(m.getUserId())
                .writerEmail(m.getEmail())
                .writerName(m.getName())
                .question(m.getQuestion())
                .questionCreateTime(m.getQuestionCreateTime())
                .answer(m.getAnswer())
                .answerCreateTime(m.getAnswerCreateTime())
                .build();
    }
}
