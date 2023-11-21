package com.homelearn.back.qna.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class QuestionListInputSpec {
    private Integer startIndex; // 시작 번호
    private Integer count; //몇개 뽑을지
}
