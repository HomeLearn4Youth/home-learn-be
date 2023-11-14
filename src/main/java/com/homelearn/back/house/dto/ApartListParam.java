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
public class ApartListParam {
    private Long dongCode; // 동코드
    private Integer startIndex; // 시작 번호
    private Integer count; //몇개 뽑을지
    private Boolean isLike; //관심 영역만 출력 조건
    private Long userId; //로그인 유저

    public ApartListParam apartListInputSpecToApartListParam (ApartListInputSpec inputSpec, Long userId){
        return ApartListParam.builder()
                .dongCode(inputSpec.getDongCode())
                .startIndex(inputSpec.getStartIndex())
                .count(inputSpec.getCount())
                .isLike(inputSpec.getIsLike())
                .userId(userId)
                .build();
    }
}
