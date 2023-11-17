package com.homelearn.back.review.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@ToString
public class FindListReviewInputSpec {
    private Long aptCode; // 리뷰리스트를 볼 aptCode
    private Integer startIndex; // 시작 번호
    private Integer count; //몇개 뽑을지
}
