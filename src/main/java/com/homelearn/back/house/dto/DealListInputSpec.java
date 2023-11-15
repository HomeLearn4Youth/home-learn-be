package com.homelearn.back.house.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DealListInputSpec {
    private Long apartCode; // 볼 아파트 코드
    private Integer startIndex; // 시작 번호
    private Integer count; //몇개 뽑을지
    private String type; //어떤 종류를 뽑을지(매매, 전월세) -> 없다면 모두
}
