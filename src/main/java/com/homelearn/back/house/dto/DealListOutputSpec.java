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
public class DealListOutputSpec {
    private Long aptCode; //아파트 코드
    private String type; // 매매, 전/월세
    private String dealDate; //거래 일
    private String area; // 전용면적
    private String floor; // 층
    private String dealAmount; // 거래 가격
}