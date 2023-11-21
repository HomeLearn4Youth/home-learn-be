package com.homelearn.back.house.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ApartListParam {
    private Long dongCode; // 동코드
    private String searchApartName; // 아파트 이름 검색
    private Integer startIndex; // 시작 번호
    private Integer count; //몇개 뽑을지
    private Boolean isLike; //관심 영역만 출력 조건
    private Long userId; //로그인 유저
    private Long groupId; // 그룹아이디

    public ApartListParam apartListInputSpecToApartListParam (ApartListInputSpec inputSpec, Long userId){
        return ApartListParam.builder()
                .dongCode(inputSpec.getDongCode())
                .searchApartName(inputSpec.getSearchApartName())
                .startIndex(inputSpec.getStartIndex())
                .count(inputSpec.getCount())
                .isLike(inputSpec.getIsLike())
                .userId(userId)
                .groupId(inputSpec.getGroupId())
                .build();
    }
}
